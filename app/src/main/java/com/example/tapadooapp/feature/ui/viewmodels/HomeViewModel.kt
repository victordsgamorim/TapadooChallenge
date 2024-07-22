package com.example.tapadooapp.feature.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapadooapp.feature.domain.repository.BookRepository
import com.example.tapadooapp.feature.ui.uistates.HomeUiState
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
class HomeViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadBooks()
    }

    private fun loadBooks() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.getBooks().onStart {
                _uiState.update { HomeUiState.Loading }
            }.collectLatest { books ->
                if (books.isEmpty()) {
                    _uiState.update { HomeUiState.Empty }
                } else {
                    _uiState.update { HomeUiState.Success(books = books) }
                }
            }
        }
    }

    suspend fun searchBooks(value: String) {
        repository.searchBooks(value).collectLatest { books ->
            if (books.isEmpty()) {
                _uiState.update { HomeUiState.Empty }
            } else {
                _uiState.update { HomeUiState.Success(books = books) }
            }
        }
    }
}