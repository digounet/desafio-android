package com.pablo.desafio.model.persistence

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.datasource.local.IMovieLocalDatasource
import com.pablo.desafio.model.persistence.datasource.remote.IMovieRemoteDatasource
import io.reactivex.Flowable
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieLocalDatasource: IMovieLocalDatasource, private val movieRemoteDatasource: IMovieRemoteDatasource) : IMovieRepository {
    override fun loadMovies(forceRemote: Boolean, page: Int, size: Int): Flowable<List<Movie>> {
        return if (forceRemote) {
            loadFromRemote(page, size)
        } else {
            movieLocalDatasource.loadMovies().take(1)
        }
    }

    private fun loadFromRemote(page: Int, size: Int): Flowable<List<Movie>> {
        return movieRemoteDatasource.loadMovies(page, size)
                .doOnEach {
                    if (it.value != null) {
                        saveMovies(it.value!!)
                    }
                }
    }

    private fun saveMovies(movies: List<Movie>) {
        movieLocalDatasource.deleteAll()
        movieLocalDatasource.saveMovies(movies)
    }

    override fun getMovie(forceRemote: Boolean, id: String): Flowable<Movie> {
        return if (forceRemote) {
            movieRemoteDatasource.getMovie(id)
                    .doOnEach {
                        if (it.value != null) {
                            updateMovie(it.value!!)
                        }
                    }
        } else {
            movieLocalDatasource.getMovie(id).take(1)
        }
    }

    private fun updateMovie(movie: Movie) {
        movieLocalDatasource.updateMovie(movie)
    }
}