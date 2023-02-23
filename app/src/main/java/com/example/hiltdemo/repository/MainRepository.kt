package com.example.hiltdemo.repository

import com.example.hiltdemo.api.MovieApi
import com.example.hiltdemo.dao.MovieDao
import com.example.hiltdemo.modal.MovieList
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: MovieDao,
    private val movieApi: MovieApi) {

    suspend fun getNowShowingMovie(page: Int) =
        movieApi.getNowShowingMovie(page)

    suspend fun insertMovie(movieList: MovieList){
        dao.insertMovie(movieList)
    }

    suspend fun deleteMovie(){
        dao.deleteMovie()
    }
}