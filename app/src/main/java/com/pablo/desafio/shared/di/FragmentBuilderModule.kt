package com.pablo.desafio.shared.di

import com.pablo.desafio.features.detail.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    fun contributeMovieDetailFragment(): MovieDetailFragment
}