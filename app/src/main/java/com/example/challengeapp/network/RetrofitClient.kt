package com.example.challengeapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Generates a retrofit client with a logging interceptor attached to it
 * - retrofit client is used to create the required service to perform network operations
 */
class RetrofitClient{

    private val BASE_URL: String = " https://itunes.apple.com/"

    fun provideRetrofitService(): TunesService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(TunesService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

}