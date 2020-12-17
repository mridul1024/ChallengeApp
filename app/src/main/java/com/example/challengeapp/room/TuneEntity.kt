package com.example.challengeapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tune_table")
data class TuneEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "artist_name")
    var artistName: String,

    @ColumnInfo(name = "track_name")
    var trackName: String,
)
