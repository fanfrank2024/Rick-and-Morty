package com.example.huma.data

class FigureRepository(
    private val apiService: ApiService
) {

    suspend fun getFigures(): List<Figure> {
        return apiService.getFigures()
    }
}