package com.example.challengeapp.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.challengeapp.network.JsonData
import com.example.challengeapp.network.RetrofitClient
import com.example.challengeapp.network.TunesService
import com.example.challengeapp.room.TuneDatabase
import com.example.challengeapp.room.TuneEntity
import com.example.challengeapp.utils.EntityMapper
import com.example.challengeapp.utils.TaskHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository class
 * -Takes care of fetch operations
 * -contains function to fetch from database
 * -contains function to fetch from network and store in database
 */
class Repository {

    var taskHandler = TaskHandler()
    var entityMapper = EntityMapper()
    var retrofitClient = RetrofitClient()
    var tuneService: TunesService = retrofitClient.provideRetrofitService()
    lateinit var tuneDatabase: TuneDatabase //= Room.databaseBuilder(context, TuneDatabase::class.java, "tune_database").build()

    fun fetchDatabase(dbInstance: TuneDatabase): LiveData<List<TuneEntity>> {
        tuneDatabase = dbInstance
        return tuneDatabase.getTuneDao().getTuneList()
    }

    fun fetchFromNetwork(searchString: String, dbInstance: TuneDatabase){
        tuneDatabase = dbInstance
        var data: Call<JsonData> = tuneService.getTunes(searchString)
        data.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                response.body()?.let { cacheData(it, it.resultCount) }
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                Log.d(Companion.TAG, "onFailure: problem fetching data")
            }

        })
    }

    private fun cacheData(data:JsonData,count:Int) {
        Log.d(TAG, "cacheData: CACHING DATA STARTED")
        taskHandler.submitTask(Runnable {
            tuneDatabase.getTuneDao().deleteTune()
            for(item in data.results.indices){
                tuneDatabase.getTuneDao().insertTune(entityMapper.toEntity(item, data.results[item].artistName, data.results[item].trackName))
            }
        })
    }

    companion object {
        private const val TAG: String = "Repository"
    }
}