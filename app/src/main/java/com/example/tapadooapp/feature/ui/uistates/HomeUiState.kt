package com.example.tapadooapp.feature.ui.uistates

import com.example.tapadooapp.feature.domain.model.Book
import kotlinx.coroutines.flow.Flow

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data object Empty : HomeUiState()
    data class Success(val books: List<Book>) : HomeUiState()
}