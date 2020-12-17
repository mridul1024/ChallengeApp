package com.example.challengeapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.challengeapp.R
import com.example.challengeapp.adapter.TuneAdapter
import com.example.challengeapp.room.TuneDatabase
import com.example.challengeapp.room.TuneEntity
import com.example.challengeapp.utils.ConnectivityStatus
import com.example.challengeapp.viewmodel.TunesViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var gridLayoutManager: GridLayoutManager

    private lateinit var tunesViewModel: TunesViewModel
    private lateinit var databaseInstance: TuneDatabase

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkRequest: NetworkRequest
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private var connectionStatus: ConnectivityStatus = ConnectivityStatus.NOT_CONNECTED

    private var lastSearchTerm: String? = null

    private var toastMessage: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        checkInternetConnectivity()

        editTextSearch = findViewById(R.id.edit_text_search)
        buttonSearch = findViewById(R.id.button_search)
        progressBar = findViewById(R.id.progress_bar)
        gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView = findViewById(R.id.recycler_view_list)

        tunesViewModel = ViewModelProvider(this).get(TunesViewModel::class.java)
        databaseInstance = Room.databaseBuilder(this, TuneDatabase::class.java, "tune_database").build()

        buttonSearch.setOnClickListener {
            showProgressBar()
            if(editTextSearch.text.isNotEmpty() && lastSearchTerm != null && lastSearchTerm.equals(editTextSearch.text.toString().trim())){
                tunesViewModel.getRecordsFromDatabase(databaseInstance)
            }
           else if(editTextSearch.text.isNotEmpty() && (connectionStatus == ConnectivityStatus.NOT_CONNECTED || connectionStatus == ConnectivityStatus.DISCONNECTED)){
               tunesViewModel.getRecordsFromDatabase(databaseInstance)
               displayToastMessage("No internet connection....connect to internet and try again")
                hideProgressBar()
           }
           else if(editTextSearch.text.isNotEmpty() && connectionStatus == ConnectivityStatus.CONNECTED) {
                lastSearchTerm = editTextSearch.text.toString()
               getSearchResults(editTextSearch.text.toString().trim(), databaseInstance)
               observeLiveData(databaseInstance)
           }
           else{
               displayToastMessage("Search term is empty")
           }

        }

        observeLiveData(databaseInstance)
    }

    /**
     * function for retrieving cached data from database
     */
    private fun getSearchResults(string: String, dbInstance: TuneDatabase){
        tunesViewModel.getRecordsFromDatabase(dbInstance)
        tunesViewModel.performNetworkOperation(string, dbInstance)
    }

    /**
     * LiveData observer function which keeps track of data-set changes
     */
    private fun observeLiveData(dbInstance: TuneDatabase){
        tunesViewModel.getViewModelData(dbInstance)?.observe(this, Observer {
                if(it == null){
                 displayToastMessage("No search results to show")
                }
                else{
                    changeAdapter(it)
                    displaySearchResult()
                }
        })
        hideProgressBar()
    }

    /**
     *Function for initializing recycler view adapter and layout manager
     */
    private fun displaySearchResult() {
        recyclerView.apply {
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.layoutManager = gridLayoutManager
        }
    }

    /**
     * Change adapter after receiving data from database
     */
    private fun changeAdapter(dataSet: List<TuneEntity>){
        recyclerViewAdapter = TuneAdapter(dataSet)
    }

    /**
     * Function to keep track of network status
     * NOT_CONNECTED
     * CONNECTED
     * DISCONNECTED
     */
    private fun checkInternetConnectivity(){
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkRequest = NetworkRequest.Builder().build()
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                connectionStatus = ConnectivityStatus.CONNECTED
                displayToastMessage("Connected to internet")
            }

            override fun onUnavailable() {
                super.onUnavailable()
                connectionStatus = ConnectivityStatus.DISCONNECTED
                displayToastMessage("disconnected to internet")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                connectionStatus = ConnectivityStatus.NOT_CONNECTED
                displayToastMessage("No internet connection available")
            }

        }

    }

    /**
     * Show and hide progress bar
     */
    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

    private fun displayToastMessage(message: String) {
        toastMessage?.cancel()
        toastMessage = makeText(this, message, LENGTH_LONG)
        toastMessage?.show()
    }

    override fun onResume() {
        super.onResume()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        connectivityManager.requestNetwork(networkRequest, networkCallback, 2500)
    }

    override fun onPause() {
        super.onPause()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}