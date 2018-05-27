package com.pablo.desafio.model.persistence.api

import com.pablo.desafio.CustomRobolectricTestRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(CustomRobolectricTestRunner::class)
class MovieServiceTest {

    @Inject
    lateinit var service: MovieService

    @Before
    fun setup() {
        //val component = DaggerTestComponent().builder()
    }

    @Test
    fun testGelAll() {
        service.loadMovies(0, 3).test()
                .assertValueCount(1)
    }

    @Test
    fun testGetById() {
        service.loadDetail("59e8ee09f36d280364369ead")
                .test()
                .assertValue { it.id == "59e8ee09f36d280364369ead" }
    }
}