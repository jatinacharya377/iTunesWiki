package com.itunes.ituneswiki.repository

import com.itunes.ituneswiki.api.ITunesService
import com.itunes.ituneswiki.di.DaggerApiComponent
import com.itunes.ituneswiki.model.ITunesSongResults
import io.reactivex.Single
import javax.inject.Inject

class ITunesRepo {

    @Inject
    lateinit var iTunesService: ITunesService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getSongs(searchTerm: String): Single<ITunesSongResults> {

        return iTunesService.api.getSongs(searchTerm)
    }
}