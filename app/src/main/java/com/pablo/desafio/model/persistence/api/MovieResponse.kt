package com.pablo.desafio.model.persistence.api

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("_id") var id: String, var name: String, var url: String)