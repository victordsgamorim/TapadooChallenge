package com.example.tapadooapp.feature.ui.uistates

import com.example.tapadooapp.feature.domain.model.Book

sealed class FavouriteUiState {
    data object Empty : FavouriteUiState()
    data object Loading : FavouriteUiState()
    data class Success(val books: List<Book>) : FavouriteUiState()

}