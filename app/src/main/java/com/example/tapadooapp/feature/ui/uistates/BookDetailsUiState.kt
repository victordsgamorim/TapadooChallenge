package com.example.tapadooapp.feature.ui.uistates

import com.example.tapadooapp.feature.domain.model.Book

sealed class BookDetailsUiState {
    data object Loading : BookDetailsUiState()
    data object Empty : BookDetailsUiState()
    data class Success(val book: Book) : BookDetailsUiState()
}