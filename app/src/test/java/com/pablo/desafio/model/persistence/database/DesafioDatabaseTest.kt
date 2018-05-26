package com.pablo.desafio.model.persistence.database

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.pablo.desafio.CustomRobolectricTestRunner
import com.pablo.desafio.model.data.Movie
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import java.io.IOException


@RunWith(CustomRobolectricTestRunner::class)
class DesafioDatabaseTest {

    private lateinit var mDao: MovieDao
    private lateinit var mDb: DesafioDatabase

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = RuntimeEnvironment.application
        mDb = Room.inMemoryDatabaseBuilder(context, DesafioDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        mDao = mDb.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDb.close()
    }

    @Test
    fun testWriteAndGetById() {
        val movie = Movie("id", "name", "description", "url")
        mDao.save(movie)

        mDao.getMovie(movie.id).test().assertValue{it.id == movie.id}
    }

    @Test
    fun testWriteAndDeleteAll() {
        mDao.deleteAll()

        val movie2 = Movie("id2", "name", "description", "url")
        val movie3 = Movie("id3", "name", "description", "url")

        mDao.save(movie2)
        mDao.save(movie3)

        mDao.getMovie(movie2.id).test().assertValue{it.id == movie2.id}

        mDao.deleteAll()

        mDao.loadMovies().test().assertValue{
            it.isEmpty()
        }
    }

    @Test
    fun testWriteAndGetAll() {
        mDao.deleteAll()

        val movie4 = Movie("id4", "name", "description", "url")
        val movie5 = Movie("id5", "name", "description", "url")

        mDao.save(movie4)
        mDao.save(movie5)

        mDao.loadMovies().test().assertValueCount(1)
        mDao.loadMovies().test().assertValue {
            it[0].id == movie4.id && it[1].id == movie5.id
        }
    }
}