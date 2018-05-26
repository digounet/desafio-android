package com.pablo.desafio.model.persistence.datasource.local

import com.pablo.desafio.model.data.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface IMovieLocalDatasource {
    fun loadMovies(): Flowable<List<Movie>>

    fun getMovie(id: String): Single<Movie>

    fun deleteAll()

    fun saveMovies(movies: List<Movie>)
}