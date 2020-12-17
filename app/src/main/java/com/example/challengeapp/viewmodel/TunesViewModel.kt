package com.example.challengeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challengeapp.repo.Repository
import com.example.challengeapp.room.TuneDatabase
import com.example.challengeapp.room.TuneEntity

/**
 * ViewModel class which communicates with repository in order to perform operations and retrieve LiveData data-sets
 */
class TunesViewModel : ViewModel() {

    private var repository = Repository()
    private var tuneLiveData: LiveData<List<TuneEntity>>? = null


    fun performNetworkOperation(searchString: String, dbInstance: TuneDatabase){
        repository.fetchFromNetwork(searchString, dbInstance)
    }

    fun getRecordsFromDatabase(dbInstance: TuneDatabase){
        tuneLiveData = repository.fetchDatabase(dbInstance)
    }

    fun getViewModelData(dbInstance: TuneDatabase): LiveData<List<TuneEntity>>? {
        return tuneLiveData
    }
}