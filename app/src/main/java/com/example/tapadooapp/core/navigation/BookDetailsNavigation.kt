package com.example.tapadooapp.core.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.tapadooapp.feature.ui.screens.BookDetailsScreen
import com.example.tapadooapp.feature.ui.viewmodels.BookDetailsViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Serializable
data class BookDetailsNavigation(val id: String) {
    companion object {
        const val BOOK_ID = "id"
    }
}

fun NavGraphBuilder.bookDetailsScreen(navController: NavController) {
    composable<BookDetailsNavigation> {
        val viewModel = hiltViewModel<BookDetailsViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        val scope = rememberCoroutineScope()
        BookDetailsScreen(
            uiState = uiState,
            onFavouriteClick = { book ->
                scope.launch {
                    viewModel.setAsFavourite(book)
                }
            },
            onBackClient = {
                navController.popBackStack()
            }
        )
    }
}

fun NavController.navigateToBookDetails(id: Int, navOptions: NavOptions? = null) {
    navigate(BookDetailsNavigation(id = id.toString()), navOptions)
}

