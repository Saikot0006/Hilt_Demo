package com.example.hiltdemo.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hiltdemo.dao.MovieDao
import com.example.hiltdemo.modal.MovieList

@androidx.room.Database(entities = [MovieList::class], version = 1)
abstract class Database : RoomDatabase(){

    abstract fun getDao() : MovieDao

    companion object{
        @Volatile
        private var db : Database? = null

        fun getDB(context: Context) : Database {

            if(db==null){
                synchronized(this){
                    db = Room.databaseBuilder(
                        context,
                        Database::class.java,
                        "hilt_db"
                    ).build()
                }
            }
            return db!!
        }

    }
}