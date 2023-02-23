package com.example.hiltdemo.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltdemo.modal.NowShowingMovieModel
import com.example.hiltdemo.repository.MainRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository)
    : ViewModel(){

    val responseBody = MutableLiveData<NowShowingMovieModel>()

    fun getNowShowingMovie(page: Int) : MutableLiveData<NowShowingMovieModel>{
        viewModelScope.launch {
            repository.getNowShowingMovie(page).let {response ->
                if(response.isSuccessful){
                    responseBody.postValue(response.body())
                }else{
                    Log.e("hello", "getAllData: hello" )
                }
            }
        }

        return responseBody
    }



}