package com.example.hiltdemo.api

import com.example.hiltdemo.modal.NowShowingMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing?api_key=19c1c2d504f8ac3c45453893ebb0e54d&language=en-US")
    suspend fun getNowShowingMovie(@Query("page") page : Int) : Response<NowShowingMovieModel>
}