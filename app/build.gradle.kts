plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")

    kotlin("android")
    kotlin("android.extensions")

}
android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.ansgar.harbor"
        minSdkVersion(18)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    // To turn on experimental feature of Kotlin
    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs","include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // Material design
    implementation("com.google.android.material:material:1.1.0-alpha10")

    // Dagger
    kapt("com.google.dagger:dagger-compiler:2.18")

    // Litho
    implementation("com.facebook.litho:litho-core:0.30.0")
    implementation("com.facebook.litho:litho-widget:0.30.0")

    kapt("com.facebook.litho:litho-processor:0.30.0")

    // SoLoader
    implementation("com.facebook.soloader:soloader:0.6.0")

    // For integration with Fresco
    implementation("com.facebook.litho:litho-fresco:0.30.0")

    // For testing
    testImplementation("com.facebook.litho:litho-testing:0.25.0")

    // RxJava2
    // Note: RxKotlin will cover RxJava 2.2.10
    implementRx()
}

apply(plugin = "MyPlugin")