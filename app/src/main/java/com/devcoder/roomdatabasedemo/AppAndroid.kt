package com.devcoder.roomdatabasedemo

import android.app.Application
import androidx.work.*
import com.devcoder.roomdatabasedemo.worker.UpdateWork
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class AppAndroid :Application() {

    override fun onCreate() {
        super.onCreate()
//        setUpWork()
    }

    private fun setUpWork() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeRequest = OneTimeWorkRequest.Builder(UpdateWork::class.java)
            .setConstraints(constraint).build()
        val workRequest = PeriodicWorkRequest.Builder(UpdateWork::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(oneTimeRequest)
    }
}