package com.ansgar.harbor

import android.app.Application
import com.facebook.soloader.SoLoader

class HarborApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
    }
}