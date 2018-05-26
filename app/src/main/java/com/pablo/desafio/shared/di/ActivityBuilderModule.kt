package com.pablo.desafio.shared.di

import com.pablo.desafio.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [(FragmentBuilderModule::class)])
    fun mainActivity(): MainActivity
}