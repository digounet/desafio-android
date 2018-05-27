package com.pablo.desafio.model.persistence.api

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movies/list")
    fun loadMovies(@Query("page") page: Int, @Query("size") size: Int): Flowable<List<MovieResponse>>

    @GET("movies/detail/{id}")
    fun loadDetail(@Path("id") id: String): Flowable<MovieDetailResponse>
}