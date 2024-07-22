package com.example.tapadooapp.feature.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapadooapp.core.navigation.BookDetailsNavigation
import com.example.tapadooapp.feature.domain.model.Book
import com.example.tapadooapp.feature.domain.repository.BookRepository
import com.example.tapadooapp.feature.ui.uistates.BookDetailsUiState
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
class BookDetailsViewModel @Inject constructor(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<BookDetailsUiState>(BookDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadBook()
    }

    private fun loadBook() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            val bookId = savedStateHandle.get<String>(BookDetailsNavigation.BOOK_ID)
            repository.getBookById(bookId!!.toInt()).onStart {
                _uiState.update { BookDetailsUiState.Loading }
            }.collectLatest { book ->
                _uiState.update { BookDetailsUiState.Success(book) }
            }
        }
    }

    suspend fun setAsFavourite(book: Book) {
        repository.setAsFavourite(book.id)
    }
}