package com.ansgar.harbor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ansgar.harbor.component.ComponentActivity
import com.ansgar.harbor.coroutine.CoroutineActivity
import com.ansgar.harbor.library.LithoActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class HarborActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    private val exceptionHandler = Handler {
        check(it.what != 1) {
            // Update view component text, this is allowed.
            "HarborApplication/Exception from other handler"
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEntries()
    }

    override fun onResume() {
        super.onResume()

        val startTime = System.currentTimeMillis()
        disposable = Observable.zip<Int, Int, Int>(
            networkCallObservable10000(1, startTime),
            networkCallObservable10(2, startTime),
            BiFunction { t1, t2 -> t1 })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("RxJava", "End stream $it after "
                    + (System.currentTimeMillis() - startTime) + " ms ")
            }, {
                // throw IllegalStateException()
            })
    }

    override fun onStart() {
        super.onStart()

        // Way to hook callback on custom handler
        val callbackFields = exceptionHandler.javaClass.getDeclaredField("mCallback")
        callbackFields.isAccessible = true
        callbackFields.set(exceptionHandler, HarborApplication.MainHandlerProxy(exceptionHandler))

        val exceptionMessage = Message()
        exceptionMessage.what = 1
        // Put the message in main thread message queue to trigger exception in handler.
        exceptionHandler.sendMessageDelayed(exceptionMessage, 1000)
    }

    private fun setEntries() {
        mapOf(
            entry_litho to LithoActivity::class.java,
            entry_component to ComponentActivity::class.java,
            entry_coroutine to CoroutineActivity::class.java
        ).forEach { (entry, clazz) ->
            entry.setOnClickListener {
                startActivity(Intent(this, clazz))
            }
        }
        entry_exception.setOnClickListener {
            throw IllegalStateException()
        }
    }

    private fun networkCallObservable10(ref: Int, startTime: Long) =
        Observable.create<Int> { emitter ->
            for (i in 0..1000) {
                if (i % 1000 == 0) {
//                Log.d("RxJava", "Success $ref for $i " + (System.currentTimeMillis() - startTime) + " ms ")
                }
            }
            emitter.onNext(ref)
        }
//            .subscribeOn(Schedulers.io())
            .doOnNext { Log.d("RxJava", "Success $ref at ${Thread.currentThread().name} in " + (System.currentTimeMillis() - startTime) + " ms ") }


    private fun networkCallObservable10000(ref: Int, startTime: Long) =
        Observable.create<Int> { emitter ->
            for (i in 0..1000000) {
                if (i % 1000 == 0) {
//                    Log.d("RxJava", "Success $ref for $i " + (System.currentTimeMillis() - startTime) + " ms ")
                }
            }
            emitter.onNext(ref)
        }
//            .subscribeOn(Schedulers.io())
            .doOnNext { Log.d("RxJava", "Success $ref at ${Thread.currentThread().name} in " + (System.currentTimeMillis() - startTime) + " ms ") }

}
