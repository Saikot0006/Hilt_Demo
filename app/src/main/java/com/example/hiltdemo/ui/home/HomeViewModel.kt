package com.example.hiltdemo.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltdemo.dao.MovieDao
import com.example.hiltdemo.modal.MovieList
import com.example.hiltdemo.modal.NowShowingMovieModel
import com.example.hiltdemo.repository.MainRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dao: MovieDao,
    private val repository: MainRepository)
    : ViewModel(){

    val responseBody = MutableLiveData<NowShowingMovieModel>()

    fun getNowShowingMovie(page: Int) : MutableLiveData<NowShowingMovieModel>{
        viewModelScope.launch {
            repository.getNowShowingMovie(page).let {response ->
                if(response.isSuccessful){
                    try {
                        repository.deleteMovie()
                        responseBody.postValue(response.body())
                        response.body().let {
                            it!!.results.forEach {
                                val movie = MovieList(
                                    id = it.id,
                                    original_title = it.original_title,
                                    overview = it.overview,
                                    title = it.title,
                                    vote_average = it.vote_average)

                                repository.insertMovie(movie)
                            }
                        }
                    }catch (e : Exception){}


                }else{
                    Log.e("hello", "getAllData: hello" )
                }
            }
        }

        return responseBody
    }




}