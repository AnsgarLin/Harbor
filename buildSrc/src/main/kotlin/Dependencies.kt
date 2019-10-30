import org.gradle.kotlin.dsl.DependencyHandlerScope

object Apps {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "3.5.0"
    const val kotlin = "1.3.50"
    const val appcompat = "1.0.2"

    /* test */
    const val junit = "4.12"

    object RxJava {
        const val kotlin = "2.4.0"
        const val android = "2.1.1"
        const val debug = "1.4.0"
    }

    // Kotlin
    const val coroutines = "1.0.0"
    const val lifecycle = "2.2.0-alpha01"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.RxJava.kotlin}"
const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RxJava.android}"
const val rxdebug = "com.akaita.java:rxjava2-debug:${Versions.RxJava.debug}"

fun DependencyHandlerScope.implementRx() {
    add("implementation", rxkotlin)
    add("implementation", rxAndroid)
    add("implementation", rxdebug)
}

const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.coroutines}"
const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

fun DependencyHandlerScope.implementCoroutine() {
    add("implementation", core)
    add("implementation", rx2)
    add("implementation", android)
    add("implementation", viewmodel)
    add("implementation", livedata)
    add("implementation", runtime)
}