package com.example.tapadooapp.core.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.tapadooapp.feature.ui.screens.FavouriteScreen
import com.example.tapadooapp.feature.ui.viewmodels.FavouriteViewModel
import kotlinx.serialization.Serializable

@Serializable
object FavouriteNavigation

fun NavGraphBuilder.favouriteScreen(onBookClick: (bookId: Int) -> Unit) {
    composable<FavouriteNavigation> {

        val viewModel = hiltViewModel<FavouriteViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        FavouriteScreen(uiState = uiState, onBookClick = onBookClick)
    }
}

fun NavController.navigateToFavourite(
    navOptions: NavOptions? = null
) {
    navigate(FavouriteNavigation, navOptions)
}

