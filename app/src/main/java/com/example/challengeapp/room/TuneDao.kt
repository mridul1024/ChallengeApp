package com.example.challengeapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TuneDao {

    @Query("SELECT * FROM tune_table")
    fun getTuneList(): LiveData<List<TuneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTune(tune: TuneEntity)

    @Query("DELETE FROM tune_table")
    fun deleteTune()
}