package com.example.huma.data

class FigureRepository(
    private val apiService: ApiService
) {

    suspend fun getFigures(): List<Figure> {
        val allFigures = mutableListOf<Figure>()
        var currentPage = 1

        while (true) {
            val response = apiService.getFigures(currentPage)
            allFigures.addAll(response.results)

            if (response.info.next == null) break
            currentPage++
        }
        return allFigures
    }
}