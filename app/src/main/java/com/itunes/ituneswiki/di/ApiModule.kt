package com.itunes.ituneswiki.di

import com.itunes.ituneswiki.api.ITunesAPI
import com.itunes.ituneswiki.api.ITunesService
import com.itunes.ituneswiki.repository.ITunesRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private var baseUrl: String = "https://itunes.apple.com/"

    @Provides
    fun getITunesApi(): ITunesAPI {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ITunesAPI::class.java)
    }

    @Provides
    fun getITunesRepo(): ITunesRepo {

        return ITunesRepo()
    }

    @Provides
    fun getITunesService(): ITunesService {

        return ITunesService()
    }
}