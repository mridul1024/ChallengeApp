package com.example.challengeapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class JsonData (

    @SerializedName("resultCount")
    @Expose
    var resultCount : Int,

    @SerializedName("results")
    @Expose
    var results : List<Result>
)