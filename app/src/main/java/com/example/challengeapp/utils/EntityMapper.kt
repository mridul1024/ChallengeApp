package com.example.challengeapp.utils

import com.example.challengeapp.room.TuneEntity

/**
 * Mapper class to refactor JSON data which is fetched from network into a more easily manageable form
 * -Converts the JSON data into TuneEntity for inserting into room database
 */
class EntityMapper{
    fun toEntity(id: Int, artistName: String, trackName: String): TuneEntity{
        return TuneEntity(id, artistName, trackName)
    }

}