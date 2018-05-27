package com.pablo.desafio.model

import com.pablo.desafio.model.persistence.api.MovieServiceTest
import com.pablo.desafio.shared.di.ActivityBuilderModule
import com.pablo.desafio.shared.di.AppComponent
import com.pablo.desafio.shared.di.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AndroidInjectionModule::class), (ActivityBuilderModule::class)])
interface TestComponent : AppComponent {
    fun inject(test: MovieServiceTest)
}