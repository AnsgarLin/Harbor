package com.ansgar.harbor

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.facebook.soloader.SoLoader

/**
 * Normally, [hookMainLooper] will be enough to catch all the exception occur on main thread. But because we will post
 * a runnable to call to it, it won't get call before onResume is return. We need [hookMainHandler] to replace the
 * Callback of the handler of AndroidThread, which won't be able to catch everything since the View now has its own
 * handler.
 */
class HarborApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)

        Log.d(TAG, "Looper: " + Looper.myLooper() + ", Queue: " + Looper.myQueue())
//        hookMainLooper()
        hookMainHandler()
    }

    companion object {
        const val TAG = "HarborApplication"
        val MAIN = Handler(Looper.getMainLooper()) {
            Log.d(TAG, "" + it.what)
            true
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Looper
    ///////////////////////////////////////////////////////////////////////////
    private fun hookMainLooper() {
        /**
         * Actually, we can just trigger the Runnable directly, but it will be confusable since it will make the method
         * can only be called at the end of [onCreate].
         */
        // MonitorRunnable().run()
        MAIN.post(MonitorRunnable())
    }
    private class MonitorRunnable : Runnable {
        override fun run() {
            try {
                Log.d(TAG, "Start monitor ${Looper.myLooper()} with queue = ${Looper.myQueue()}")
                Looper.loop()
            } catch (throwable: Throwable) {
                // Restart the runnable
                MAIN.post(this)
                Log.d(TAG, "MonitorRunnable catch exception=${throwable.stackTrace}")
            }

        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Main Handler
    ///////////////////////////////////////////////////////////////////////////
    private fun hookMainHandler() {
        try {
            val start = System.currentTimeMillis()
            val clazz = Class.forName("android.app.ActivityThread")
            // ActivityThread instance
            val activityThreadField = clazz.getDeclaredField("sCurrentActivityThread")
            activityThreadField.isAccessible = true
            val activityThread = requireNotNull(activityThreadField.get(null)) { "ActivityThread.sCurrentActivityThread is null." }

            // ActivityThread mInstrumentation
//            val instrumentationField = clazz.getDeclaredField("mInstrumentation")
//            instrumentationField.isAccessible = true
//            val instrumentation = requireNotNull(instrumentationField.get(activityThread)) { "ActivityThread.mInstrumentation is null." }
//            instrumentationField.set(activityThread, InstrumentationProxy(instrumentation as Instrumentation))

            // ActivityThread mH
            val handlerField = clazz.getDeclaredField("mH")
            handlerField.isAccessible = true
            val handler = requireNotNull(handlerField.get(activityThread)) { "ActivityThread.mH is null." }

            // Proxy Handler mCallback
            val callbackField = Handler::class.java.getDeclaredField("mCallback")
            callbackField.isAccessible = true
            callbackField.set(handler, MainHandlerProxy(handler as Handler))

            Log.d(TAG, "hookMainHandler finishes in ${System.currentTimeMillis() - start}ms")
        } catch (tr: Throwable) {
            Log.d(TAG, "hookMainHandler failed")
        }
    }

    class MainHandlerProxy(private val origin: Handler) : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            try {
                origin.handleMessage(msg)
                Log.d(TAG, "MainHandlerProxy Handler: $origin / ${msg.what}")
            } catch (throwable: Throwable) {
                Log.d(TAG, "MainHandlerProxy: ${Looper.myLooper()}, Queue: ${Looper.myQueue()}, catch $throwable")
                throwable.stackTrace.forEach {
                    Log.d(TAG, "$it")
                }
            }

            return true
        }
    }
}