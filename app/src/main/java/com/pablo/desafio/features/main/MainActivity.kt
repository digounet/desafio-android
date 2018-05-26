package com.pablo.desafio.features.main

import android.os.Bundle
import com.pablo.desafio.model.persistence.IMovieRepository
import com.pablo.desafio.shared.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var repo: IMovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo.loadMovies(true, 1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach {
                    it.value?.forEach {
                        Timber.d(it.name)
                    }
                }
                .subscribe()
    }
}
