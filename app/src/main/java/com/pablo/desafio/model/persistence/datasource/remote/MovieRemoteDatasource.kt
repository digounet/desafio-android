package com.pablo.desafio.model.persistence.datasource.remote

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.api.MovieService
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class MovieRemoteDatasource @Inject constructor(val movieService: MovieService): IMovieRemoteDatasource {
    override fun loadMovies(page: Int, size: Int): Flowable<List<Movie>> {

        val movieList = ArrayList<Movie>()

        return movieService.loadMovies(page, size)
                .doOnError { Timber.e(it) }
                .doOnNext {
                    it.forEach {
                        movieList.add(Movie(it.id, it.name, "", it.url))
                    }
                }
                .map { movieList }
    }

    override fun getMovie(id: String): Single<Movie> {
        return movieService.loadDetail(id)
                .doOnError { Timber.e(it) }
                .map { Movie(it._id, it.name, it.description, it.url) }
    }
}