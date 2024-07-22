package com.example.tapadooapp.feature.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapadooapp.feature.domain.repository.BookRepository
import com.example.tapadooapp.feature.ui.uistates.FavouriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<FavouriteUiState>(FavouriteUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadFavouriteBooks()
    }

    private fun loadFavouriteBooks() {
        currentUiStateJob?.cancel()

        currentUiStateJob = viewModelScope.launch {
            repository.getFavouriteBooks().onStart {
                _uiState.update { FavouriteUiState.Loading }
            }.collectLatest { books ->
                if (books.isEmpty()) {
                    _uiState.update { FavouriteUiState.Empty }
                } else {
                    _uiState.update { FavouriteUiState.Success(books) }
                }

            }
        }
    }
}