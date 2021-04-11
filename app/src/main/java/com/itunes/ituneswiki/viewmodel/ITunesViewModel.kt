package com.itunes.ituneswiki.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itunes.ituneswiki.di.DaggerApiComponent
import com.itunes.ituneswiki.model.ITuneSongs
import com.itunes.ituneswiki.model.ITunesSongResults
import com.itunes.ituneswiki.repository.ITunesRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ITunesViewModel: ViewModel() {

    @Inject
    lateinit var iTunesRepo: ITunesRepo
    private var disposable = CompositeDisposable()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getSongs(searchTerm: String): LiveData<List<ITuneSongs>> {

        val trendingMoviesList = MutableLiveData<List<ITuneSongs>>()
        disposable.add(
                iTunesRepo.getSongs(searchTerm)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ITunesSongResults>() {
                    override fun onSuccess(value: ITunesSongResults?) {

                        trendingMoviesList.value = value?.songsList
                    }

                    override fun onError(e: Throwable?) {

                        e?.message?.let { Log.e("songs_results_error", it) }
                    }
                })
        )

        return trendingMoviesList
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}