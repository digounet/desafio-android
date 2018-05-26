package com.pablo.desafio.model.persistence.api

import com.pablo.desafio.CustomRobolectricTestRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(CustomRobolectricTestRunner::class)
class MovieServiceTest {
    private lateinit var service: MovieService

    @Before
    fun setUp() {
        service = MovieService.create()
    }

    @Test
    fun testGelAll() {
        service.loadMovies(1, 10).test()
                .assertValueCount(1)
    }

    @Test
    fun testGetById() {
        service.loadDetail("59e8ee09f36d280364369ead")
                .test()
                .assertValue { it._id.equals("59e8ee09f36d280364369ead") }
    }
}