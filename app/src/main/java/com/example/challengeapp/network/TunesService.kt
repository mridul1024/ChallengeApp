package com.example.challengeapp.network

import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET

/**
 * Retrofit service interface to connect with "/search" endpoint and receive JSON data
 */
interface TunesService {

    @GET("search")
    fun getTunes(@Query("term") searchTerm: String): Call<JsonData>
}