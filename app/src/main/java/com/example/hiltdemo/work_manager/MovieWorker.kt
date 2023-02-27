package com.example.hiltdemo.work_manager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MovieWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val workerDependency: WorkerDependency,
   // private val viewModel: HomeViewModel

) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e("work", "doWork: work start!!" )


        return Result.success()
    }


}