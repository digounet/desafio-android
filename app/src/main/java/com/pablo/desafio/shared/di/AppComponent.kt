package com.pablo.desafio.shared.di

import android.app.Application
import com.pablo.desafio.DesafioApp
import com.pablo.desafio.model.persistence.IMovieRepository
import com.pablo.desafio.model.persistence.datasource.local.IMovieLocalDatasource
import com.pablo.desafio.model.persistence.datasource.remote.IMovieRemoteDatasource
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AndroidInjectionModule::class), (ActivityBuilderModule::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(desafioApp: DesafioApp)
}