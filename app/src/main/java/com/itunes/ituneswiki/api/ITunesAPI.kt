package com.itunes.ituneswiki.api

import com.itunes.ituneswiki.model.ITunesSongResults
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesAPI {

    @GET("search")
    fun getSongs(
        @Query("term") searchTerm: String
    ): Single<ITunesSongResults>
}