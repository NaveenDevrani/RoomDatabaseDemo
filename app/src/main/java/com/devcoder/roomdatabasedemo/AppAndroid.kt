package com.devcoder.roomdatabasedemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppAndroid :Application() {

    override fun onCreate() {
        super.onCreate()
    }
}