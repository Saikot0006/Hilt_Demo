package com.example.hiltdemo.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltdemo.modal.MovieList
import com.example.hiltdemo.modal.NowShowingMovieModel
import com.example.hiltdemo.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository)
    : ViewModel(){

    val responseBody = MutableLiveData<NowShowingMovieModel>()

    fun getNowShowingMovie(page: Int) : MutableLiveData<NowShowingMovieModel>{
        viewModelScope.launch {
            repository.getNowShowingMovie(page).let {response ->
                if(response.isSuccessful){
                    try {
                        //repository.deleteMovie()
                        responseBody.postValue(response.body())
                    }catch (e : Exception){
                        Log.e("error", "getNowShowingMovie: "+e.localizedMessage )
                    }


                }else{
                    Log.e("hello", "getAllData: hello" )
                }
            }
        }

        return responseBody
    }

     fun insertMovie(movieList: MovieList){
         viewModelScope.launch {
             try {
                 repository.insertMovie(movieList)
             }catch (e:Exception){
                 Log.e("error", "insertMovie: "+e.localizedMessage )
             }

         }

    }




}