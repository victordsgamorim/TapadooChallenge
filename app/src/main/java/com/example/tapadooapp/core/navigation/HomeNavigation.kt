package com.example.tapadooapp.core.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.tapadooapp.feature.ui.screens.HomeScreen
import com.example.tapadooapp.feature.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object HomeNavigation

fun NavGraphBuilder.homeScreen(onBookClick: (bookId: Int) -> Unit) {
    composable<HomeNavigation> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        HomeScreen(
            uiState = uiState,
            onBookClick = onBookClick,
            onSearchValueChanged = { text ->
                scope.launch {
                    viewModel.searchBooks(text)
                }

            }
        )
    }
}

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(HomeNavigation, navOptions)
}

