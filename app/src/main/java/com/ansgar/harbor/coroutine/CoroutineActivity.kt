package com.ansgar.harbor.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ansgar.harbor.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.createCoroutine

class CoroutineActivity : AppCompatActivity() {
    private val suspendLambda = suspend {
        "Hello world!"
    }
    private val completion = object : Continuation<String> {
        override val context get() = EmptyCoroutineContext
        override fun resumeWith(result: Result<String>): Unit = println(result.getOrThrow())
    }
    val coroutine: Continuation<Unit> = suspendLambda.createCoroutine(completion)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        val handler = CoroutineExceptionHandler { _, _ ->
            Log.d("coroutine", "catch exception in handler")
        }

        lifecycleScope.launch(handler) {
            try {
                withContext(Dispatchers.Default) { catchSuspend() }
            } catch (e: Throwable) {
                Log.d("coroutine", "catch exception in launch: $e")
            }
        }
    }

    private suspend fun catchSuspend() = supervisorScope() {
//        val local = async {
//            exceptionSuspend()
//            throw IllegalArgumentException("coroutine/throw exception in catchSuspend")
//        }
//
//        try {
//            local.await()
//        } catch (e: Throwable) {
//            Log.d("coroutine", "catch exception in catchSuspend: $e")
//        }

//        try {
//            withContext(coroutineContext) {
//                exceptionSuspend()
//                throw IllegalArgumentException("coroutine/throw exception in catchSuspend")
//            }
//        } catch (e: Throwable) {
//            Log.d("coroutine", "catch exception in catchSuspend: $e")
//        }

        val handler = CoroutineExceptionHandler { _, _ ->
            Log.d("coroutine", "catch exception in catchSuspend handler")
        }
        lifecycleScope.launch(handler) {
//            try {
                exceptionSuspend()
                throw IllegalArgumentException("coroutine/throw exception in catchSuspend")
//            } catch (e: Throwable) {
//                Log.d("coroutine", "catch exception in catchSuspend: $e")
//            }
        }

    }

    private suspend fun exceptionSuspend() = coroutineScope {
        try {
            "coroutine/Normal return".apply {
                throw IllegalArgumentException("coroutine/throw exception")
            }
        } catch (e: Throwable) {
            Log.d("coroutine", "catch exception: $e")
        }
    }
}