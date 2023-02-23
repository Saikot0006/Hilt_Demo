package com.example.hiltdemo.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie")
data class MovieList(
    @PrimaryKey(autoGenerate = true)
    val movieID : Long = 0,
    val id : Int,
    val original_title: String,
    val overview: String,
    val title: String,
    val vote_average: Double,
)