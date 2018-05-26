package com.pablo.desafio.model.persistence.datasource.local

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.database.MovieDao
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieLocalDatasource @Inject constructor(val movieDao: MovieDao) : IMovieLocalDatasource {
    override fun loadMovies(): Flowable<List<Movie>> {
        return movieDao.loadMovies()
    }

    override fun saveMovies(movies: List<Movie>) {
        movieDao.save(movies)
    }

    override fun getMovie(id: String): Single<Movie> {
        return movieDao.getMovie(id)
    }

    override fun deleteAll() {
        movieDao.deleteAll()
    }
}