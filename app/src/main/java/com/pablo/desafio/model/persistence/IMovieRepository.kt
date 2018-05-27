package com.pablo.desafio.model.persistence

import com.pablo.desafio.model.data.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface IMovieRepository {
    fun loadMovies(forceRemote: Boolean, page: Int, size: Int): Flowable<List<Movie>>
    fun getMovie(forceRemote: Boolean, id: String): Flowable<Movie>
}