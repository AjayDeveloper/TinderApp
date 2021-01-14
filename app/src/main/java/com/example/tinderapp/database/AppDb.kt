package com.example.tinderapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tinderapp.model.Profile

@Database(entities = [(Profile::class)],version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object
    {
        private var INSTANCE: AppDb ?= null
        fun getDatabase(context: Context): AppDb?{
            if (INSTANCE == null)
            {
                synchronized(AppDb::class)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,AppDb::class.java,"Guests.db").build()
                }
            }

            return INSTANCE
        }

    }
    fun destroyInstance()
    {
        INSTANCE = null
    }
}