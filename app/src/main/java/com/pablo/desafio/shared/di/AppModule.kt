package com.pablo.desafio.shared.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.pablo.desafio.model.persistence.IMovieRepository
import com.pablo.desafio.model.persistence.MovieRepository
import com.pablo.desafio.model.persistence.api.ApiConstants
import com.pablo.desafio.model.persistence.api.MovieService
import com.pablo.desafio.model.persistence.database.DesafioDatabase
import com.pablo.desafio.model.persistence.database.MovieDao
import com.pablo.desafio.model.persistence.datasource.local.IMovieLocalDatasource
import com.pablo.desafio.model.persistence.datasource.local.MovieLocalDatasource
import com.pablo.desafio.model.persistence.datasource.remote.IMovieRemoteDatasource
import com.pablo.desafio.model.persistence.datasource.remote.MovieRemoteDatasource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideMovieService(okHttpClient: OkHttpClient): MovieService {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): DesafioDatabase {
        return Room.databaseBuilder(application, DesafioDatabase::class.java, "desafio.db").build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(desafioDatabase: DesafioDatabase): MovieDao {
        return desafioDatabase.movieDao()
    }

    @Provides
    @Singleton
    internal fun provideLocalDatasource(movieDBDao: MovieDao): IMovieLocalDatasource {
        return MovieLocalDatasource(movieDBDao)
    }

    @Provides
    @Singleton
    internal fun provideRemoteDatasource(movieDBService: MovieService): IMovieRemoteDatasource {
        return MovieRemoteDatasource(movieDBService)
    }

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieLocalDatasource: IMovieLocalDatasource, imovieRemoteDatasource: IMovieRemoteDatasource): IMovieRepository {
        return MovieRepository(movieLocalDatasource, imovieRemoteDatasource)
    }
}