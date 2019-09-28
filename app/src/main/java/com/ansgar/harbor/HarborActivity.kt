package com.ansgar.harbor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.ansgar.harbor.component.ComponentActivity
import com.ansgar.harbor.library.LithoActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class HarborActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEntries()

        throw IllegalStateException()
    }

    override fun onResume() {
        super.onResume()

//        val startTime = System.currentTimeMillis()
//        disposable =
//            Observable.combineLatest<Int, Int, Int>(
//                networkCallObservable(1),
//                networkCallObservable(2), BiFunction { t1, t2 ->
//                t1
//            })
////                .mergeWith(networkCallObservable(2))
//                .subscribeOn(Schedulers.io())
//                .subscribe {
//                    Log.d("RxJava", "Main stream received $it after " + (System.currentTimeMillis() - startTime) + " ms ")
//                }
//        val startTime1 = System.currentTimeMillis()
//        disposable = Observable.combineLatest<Int, Int, Int>(
//            networkCallObservable10000(1, startTime1).subscribeOn(Schedulers.io()),
//            networkCallObservable10(2, startTime1).subscribeOn(Schedulers.computation()),
//            BiFunction { t1, t2 -> t1 }
//        )
//            .subscribeOn(Schedulers.computation())
//            .subscribe {
//                Log.d("RxJava", "End stream $it after "
//                    + (System.currentTimeMillis() - startTime1) + " ms ")
//            }
//        val startTime2 = System.currentTimeMillis()
//        disposable = Observable.AdPlacementReporter<Int, Int, Int>(
//            networkCallObservable10000(1, startTime2).subscribeOn(Schedulers.computation()),
//            networkCallObservable10(2, startTime2).subscribeOn(Schedulers.computation()),
//            BiFunction { t1, t2 -> t1 }
//        )
//            .subscribeOn(Schedulers.computation())
//            .subscribe {
//                Log.d("RxJava", "End stream $it after "
//                    + (System.currentTimeMillis() - startTime2) + " ms ")
//            }
        val startTime3 = System.currentTimeMillis()
        disposable = Observable.zip<Int, Int, Int>(
            networkCallObservable10000(1, startTime3),
            networkCallObservable10(2, startTime3),
            BiFunction { t1, t2 -> t1 }
        )
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Log.d("RxJava", "End stream $it after "
                    + (System.currentTimeMillis() - startTime3) + " ms ")
            }, {

                throw IllegalStateException()
            })

//        val startTime = System.currentTimeMillis()
//        disposable = networkCallObservable10000(1, startTime)
//            .subscribeOn(Schedulers.io())
//            .mergeWith(networkCallObservable10(2, startTime)
//                .subscribeOn(Schedulers.computation()))
//            .subscribe {
//                Log.d("RxJava", "End stream $it after "
//                    + (System.currentTimeMillis() - startTime) + " ms ")
//            }
//        val startTime = System.currentTimeMillis()
//        disposable = networkCallObservable10000(1, startTime)
//            .subscribeOn(Schedulers.computation())
//                .mergeWith(networkCallObservable10(2, startTime)
//                    .subscribeOn(Schedulers.computation()))
//                .subscribe {
//                    Log.d("RxJava", "End stream $it after "
//                        + (System.currentTimeMillis() - startTime) + " ms ")
//                }
//        val startTime = System.currentTimeMillis()
//        disposable = networkCallObservable10000(1, startTime)
//            .mergeWith(networkCallObservable10(2, startTime))
//            .subscribeOn(Schedulers.computation())
//            .subscribe {
//                Log.d("RxJava", "End stream $it after "
//                    + (System.currentTimeMillis() - startTime) + " ms ")
//            }

//        val vd = VectorDrawableCompat.create(resources, R.drawable.ic_placeholder, null)
//        img_arrow.setImageDrawable(vd)
//        val d = ResourcesCompat.getDrawable(resources, R.drawable.android_placeholder, null)
//        img2.setImageDrawable(d)
    }

    private fun setEntries() {
        entry_litho.setOnClickListener {
            startActivity(Intent(this, LithoActivity::class.java))
        }
        entry_component.setOnClickListener {
            startActivity(Intent(this, ComponentActivity::class.java))
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
