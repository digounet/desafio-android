package com.pablo.desafio.shared.base

interface BaseContract {
    interface View

    interface Presenter<in T> {
        fun subscribe()

        fun unsubscribe()

        fun attachView(view: T)
    }
}