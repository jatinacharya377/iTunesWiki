package com.itunes.ituneswiki.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ITunesSongResults(
    @Expose
    @SerializedName("results")
    val songsList: List<ITuneSongs>
)
