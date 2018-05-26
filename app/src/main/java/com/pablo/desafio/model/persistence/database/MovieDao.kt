package com.pablo.desafio.model.persistence.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pablo.desafio.model.data.Movie
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT id, name, description, url FROM movies")
    fun loadMovies(): Flowable<List<Movie>>

    @Query("SELECT id, name, description, url FROM movies WHERE id = :id")
    fun getMovie(id: String): Single<Movie>

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: List<Movie>)
}