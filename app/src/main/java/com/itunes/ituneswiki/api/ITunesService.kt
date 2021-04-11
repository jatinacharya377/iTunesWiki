package com.itunes.ituneswiki.api

import com.itunes.ituneswiki.di.DaggerApiComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ITunesService {

    @Inject
    lateinit var api: ITunesAPI

    init {
        DaggerApiComponent.create().inject(this)
    }

}