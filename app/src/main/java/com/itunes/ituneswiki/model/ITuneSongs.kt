package com.itunes.ituneswiki.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ITuneSongs(
    @Expose
    @SerializedName("artistName")
    val artistName: String,
    @Expose
    @SerializedName("trackName")
    val trackName: String,
    @Expose
    @SerializedName("artworkUrl100")
    val trackUrl: String
)
