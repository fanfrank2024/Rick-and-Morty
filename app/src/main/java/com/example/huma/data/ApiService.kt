package com.example.huma.data

import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getFigures(): List<Figure>
}