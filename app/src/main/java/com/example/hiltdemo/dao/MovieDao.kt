package com.example.hiltdemo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hiltdemo.modal.MovieList

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movieList : MovieList)

    @Query("delete from tbl_movie")
    suspend fun deleteMovie()
}