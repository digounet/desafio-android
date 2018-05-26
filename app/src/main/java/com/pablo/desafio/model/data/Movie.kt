package com.pablo.desafio.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey
        var id: String,

        var name: String,

        var description: String,

        var url: String
)