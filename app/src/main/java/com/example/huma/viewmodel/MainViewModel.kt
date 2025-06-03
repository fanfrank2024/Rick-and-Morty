package com.example.huma.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huma.data.FigureRepository
import com.example.huma.data.Figure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val figureRepository: FigureRepository
): ViewModel() {

    private val _figures = MutableStateFlow<List<Figure>>(emptyList())
    val figures: StateFlow<List<Figure>> = _figures

    val searchQuery = MutableStateFlow("")

    private fun getFigures() {
        viewModelScope.launch {
            try {
                _figures.value = figureRepository.getFigures()
            } catch(e: Exception) {
                e.printStackTrace()
                _figures.value = emptyList()
            }
        }
    }

    val filteredFigures = combine(figures, searchQuery) { figure, query ->
        if(query.isBlank()) {
            figure
        } else {
            figure.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

    init {
        getFigures()
    }
}