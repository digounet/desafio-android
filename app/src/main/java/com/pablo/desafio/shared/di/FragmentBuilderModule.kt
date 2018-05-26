package com.pablo.desafio.shared.di

import com.pablo.desafio.features.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    fun contributeMovieDetailFragment(): DetailFragment
}