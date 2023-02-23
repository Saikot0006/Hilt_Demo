package com.example.hiltdemo.repository

import com.example.hiltdemo.api.MovieApi
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val movieApi: MovieApi) {

    suspend fun getNowShowingMovie(page: Int) =
        movieApi.getNowShowingMovie(page)
}