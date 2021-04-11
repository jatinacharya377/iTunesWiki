package com.itunes.ituneswiki.di

import com.itunes.ituneswiki.api.ITunesService
import com.itunes.ituneswiki.repository.ITunesRepo
import com.itunes.ituneswiki.viewmodel.ITunesViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: ITunesService)
    fun inject(repo: ITunesRepo)
    fun inject(viewModel: ITunesViewModel)
}