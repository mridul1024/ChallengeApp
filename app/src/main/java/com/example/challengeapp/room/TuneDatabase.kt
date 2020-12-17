package com.example.challengeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TuneEntity::class], version = 1)
abstract class TuneDatabase : RoomDatabase(){

    abstract fun getTuneDao(): TuneDao
}