package com.example.challengeapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("wrapperType")
    @Expose
    var wrapperType : String,

    @SerializedName("kind")
    @Expose
    var kind : String,

    @SerializedName("collectionId")
    @Expose
    var collectionId : Integer,

    @SerializedName("trackId")
    @Expose
    var trackId : Integer,

    @SerializedName("artistName")
    @Expose
    var artistName : String,

    @SerializedName("collectionName")
    @Expose
    var collectionName : String,

    @SerializedName("trackName")
    @Expose
    var trackName : String,

    @SerializedName("collectionCensoredName")
    @Expose
    var collectionCensoredName : String,

    @SerializedName("trackCensoredName")
    @Expose
    var trackCensoredName : String,

    @SerializedName("collectionArtistId")
    @Expose
    var collectionArtistId : Integer,

    @SerializedName("collectionArtistViewUrl")
    @Expose
    var collectionArtistViewUrl : String,

    @SerializedName("collectionViewUrl")
    @Expose
    var collectionViewUrl : String,

    @SerializedName("trackViewUrl")
    @Expose
    var trackViewUrl : String,

    @SerializedName("previewUrl")
    @Expose
    var previewUrl : String,

    @SerializedName("artworkUrl30")
    @Expose
    var artworkUrl30: String,

    @SerializedName("artworkUrl60")
    @Expose
    var artworkUrl60 : String,

    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100: String,

    @SerializedName("collectionPrice")
    @Expose
    var  collectionPrice: Double,

    @SerializedName("trackPrice")
    @Expose
    var trackPrice : Double,

    @SerializedName("trackRentalPrice")
    @Expose
    var trackRentalPrice : Double,

    @SerializedName("collectionHdPrice")
    @Expose
    var collectionHdPrice: Double,

    @SerializedName("trackHdPrice")
    @Expose
    var trackHdPrice: Double,

    @SerializedName("trackHdRentalPrice")
    @Expose
    var trackHdRentalPrice: Double,

    @SerializedName("releaseDate")
    @Expose
    var releaseDate: String,

    @SerializedName("collectionExplicitness")
    @Expose
    var collectionExplicitness: String,

    @SerializedName("trackExplicitness")
    @Expose
    var trackExplicitness: String,

    @SerializedName("discCount")
    @Expose
    var discCount: Int,

    @SerializedName("discNumber")
    @Expose
    var discNumber: Int,

    @SerializedName("trackCount")
    @Expose
    var trackCount: Int,

    @SerializedName("trackNumber")
    @Expose
    var trackNumber: Int,

    @SerializedName("trackTimeMillis")
    @Expose
    var trackTimeMillis: Int,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("currency")
    @Expose
    var  currency: String,

    @SerializedName("primaryGenreName")
    @Expose
    var  primaryGenreName: String,

    @SerializedName("contentAdvisoryRating")
    @Expose
    var contentAdvisoryRating: String,

    @SerializedName("shortDescription")
    @Expose
    var  shortDescription: String,

    @SerializedName("longDescription")
    @Expose
    var  longDescription: String,

    @SerializedName("hasITunesExtras")
    @Expose
    var  hasITunesExtras: Boolean
)