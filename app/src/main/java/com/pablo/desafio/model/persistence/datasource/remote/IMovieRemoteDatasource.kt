package com.pablo.desafio.model.persistence.datasource.remote

import com.pablo.desafio.model.data.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface IMovieRemoteDatasource {
    fun loadMovies(page: Int, size: Int): Flowable<List<Movie>>
    fun getMovie(id: String): Single<Movie>
}