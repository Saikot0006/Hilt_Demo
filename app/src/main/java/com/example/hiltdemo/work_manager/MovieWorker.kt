package com.example.hiltdemo.work_manager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.hiltdemo.modal.MovieList
import com.example.hiltdemo.repository.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltWorker
class MovieWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val mainRepository: MainRepository

) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.e("work", "doWork: work start!!" )
        var data : Data = inputData
        var value : Int = data.getInt("input_data",1)
        Log.e("value", "doWork: "+value )

        getNowShowingMovie(value)

        return Result.success()
    }

    private suspend fun getNowShowingMovie(value : Int) {
        Log.e("work", "doWork1: work start!!" )
        val response = mainRepository.getNowShowingMovie(value)
        for(i in 0..response.body()!!.results.size - 1){
            Log.e("work", "getNowShowingMovie: doWork6"+response.body()!!.results.get(i).original_title )
            val movie = MovieList(
                id = response.body()!!.results.get(i).id,
                title = response.body()!!.results.get(i).title,
                overview = response.body()!!.results.get(i).overview,
                original_title = response.body()!!.results.get(i).original_title,
                vote_average = response.body()!!.results.get(i).vote_average,
            )

            insertMovie(movie)

        }

    }

    private suspend fun insertMovie(movie: MovieList) {
        Log.e("work", "doWork4: work start!!" )

        mainRepository.insertMovie(movie)
    }


}