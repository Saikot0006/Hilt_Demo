package com.example.hiltdemo.dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(result: com.example.hiltdemo.modal.Result)
}