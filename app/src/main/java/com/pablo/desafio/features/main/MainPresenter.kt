package com.pablo.desafio.features.main

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.IMovieRepository
import com.pablo.desafio.model.persistence.api.ApiConstants
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainPresenter @Inject constructor(
        var repository: IMovieRepository,
        private val subscriberScheduler : Scheduler = Schedulers.io(),
        private val observerScheduler : Scheduler = AndroidSchedulers.mainThread()) : MainContract.Presenter {

    private lateinit var view: WeakReference<MainContract.View>
    private val subscriptions = CompositeDisposable()
    private val movieList : MutableList<Movie> = mutableListOf()

    override fun loadMovies(forceRemote: Boolean, page: Int) {

        val fromApi = forceRemote || movieList.isEmpty() || movieList.size < ApiConstants.API_PAGE_SIZE * (page + 1)

        val movieListObs = repository.loadMovies(fromApi, page, ApiConstants.API_PAGE_SIZE)
                    .doOnNext {
                        it.forEach {
                            if (!movieList.contains(it)) {
                                movieList.add(it)
                            }
                        }
                    }

        val subs = movieListObs
                .subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler)
                .doOnComplete {
                    Timber.e("Complete")
                    view.get()?.updateList(movieList)
                }
                .doOnError {
                    Timber.e(it)
                    view.get()?.showLoadErrorMessage()
                }
                .subscribe()

        subscriptions.add(subs)

    }

    override fun subscribe() {
        loadMovies(false, 0)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attachView(view: MainContract.View) {
        this.view = WeakReference(view)
    }
}