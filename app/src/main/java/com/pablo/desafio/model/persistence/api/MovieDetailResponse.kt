package com.pablo.desafio.model.persistence.api

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(@SerializedName("_id") var id: String, var name: String, var description: String, var url: String)