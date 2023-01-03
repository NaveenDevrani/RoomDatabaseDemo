package com.devcoder.roomdatabasedemo.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpdateWork(private val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {

//       val map = HashMap<String, String>()
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..900){
                delay(1000)
                val num = (Math.random() * 10).toInt()
//                Toast.makeText(context, "Random number $num", Toast.LENGTH_LONG).show()
                Log.d("Work for every second", "doWork: Running")
            }
        }

        return Result.success()
    }

}