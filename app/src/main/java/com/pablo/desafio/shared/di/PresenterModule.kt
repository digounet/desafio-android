package com.pablo.desafio.shared.di

import com.pablo.desafio.features.detail.MovieDetailContract
import com.pablo.desafio.features.detail.MovieDetailPresenter
import com.pablo.desafio.features.main.MainContract
import com.pablo.desafio.features.main.MainPresenter
import com.pablo.desafio.model.persistence.IMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainPresenter(repository: IMovieRepository): MainContract.Presenter = MainPresenter(repository)

    @Provides
    @Singleton
    fun provideMovieDetailPresenter(repository: IMovieRepository): MovieDetailContract.Presenter = MovieDetailPresenter(repository)
}