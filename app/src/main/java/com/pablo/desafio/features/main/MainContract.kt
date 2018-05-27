package com.pablo.desafio.features.main

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.shared.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun showMovieDetail(movie: Movie)

        fun updateList(movies : List<Movie>)

        fun showLoadErrorMessage()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadMovies(forceRemote : Boolean, page: Int)
    }
}