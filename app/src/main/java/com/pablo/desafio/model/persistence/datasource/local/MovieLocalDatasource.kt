package com.pablo.desafio.model.persistence.datasource.local

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.database.MovieDao
import io.reactivex.Flowable
import javax.inject.Inject

class MovieLocalDatasource @Inject constructor(val movieDao: MovieDao) : IMovieLocalDatasource {
    override fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    override fun loadMovies(): Flowable<List<Movie>> {
        return movieDao.loadMovies()
    }

    override fun saveMovies(movies: List<Movie>) {
        movieDao.save(movies)
    }

    override fun getMovie(id: String): Flowable<Movie> {
        return movieDao.getMovie(id)
    }

    override fun deleteAll() {
        movieDao.deleteAll()
    }
}