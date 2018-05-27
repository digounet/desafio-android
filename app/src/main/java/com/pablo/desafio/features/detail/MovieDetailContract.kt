package com.pablo.desafio.features.detail

import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.shared.base.BaseContract

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun showMovie(movie: Movie)

        fun showLoadErrorMessage()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun showMovie(movie: Movie)
    }
}