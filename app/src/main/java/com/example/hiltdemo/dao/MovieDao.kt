package com.example.hiltdemo.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.hiltdemo.modal.MovieList

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movieList : MovieList)
}