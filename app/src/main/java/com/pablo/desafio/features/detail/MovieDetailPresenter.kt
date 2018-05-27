package com.pablo.desafio.features.detail

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
        var repository: IMovieRepository,
        private val subscriberScheduler : Scheduler = Schedulers.io(),
        private val observerScheduler : Scheduler = AndroidSchedulers.mainThread())  : MovieDetailContract.Presenter {

    private lateinit var view: WeakReference<MovieDetailContract.View>
    private val subscriptions = CompositeDisposable()

    override fun showMovie(movie: Movie) {
        var localMovie = movie

        val obs = repository.getMovie(localMovie.description == "", movie.id)

        val subs = obs.subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler)
                .doOnNext {
                    localMovie = it
                }
                .doOnComplete {
                    Timber.e("Complete")
                    view.get()?.showMovie(localMovie)
                }
                .doOnError {
                    Timber.e(it)
                    view.get()?.showLoadErrorMessage()
                }
                .subscribe()

        subscriptions.add(subs)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attachView(view: MovieDetailContract.View) {
        this.view = WeakReference(view)
    }
}