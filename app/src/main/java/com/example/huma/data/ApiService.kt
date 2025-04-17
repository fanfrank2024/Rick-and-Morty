package com.example.huma.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getFigures(@Query("page") page: Int = 1): FigureResponse
}