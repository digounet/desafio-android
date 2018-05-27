package com.pablo.desafio.model.persistence.database

import android.arch.persistence.room.*
import com.pablo.desafio.model.data.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Query("SELECT id, name, description, url FROM movies")
    fun loadMovies(): Flowable<List<Movie>>

    @Query("SELECT id, name, description, url FROM movies WHERE id = :id")
    fun getMovie(id: String): Flowable<Movie>

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)
}