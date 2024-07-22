package com.example.tapadooapp.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.example.tapadooapp.feature.ui.components.BottomNavigationBarItem
import kotlinx.serialization.Serializable


@Serializable
object BottomBarGraphNavigation

fun NavGraphBuilder.bottomBarGraph(navController: NavController) {
    navigation<BottomBarGraphNavigation>(
        startDestination = HomeNavigation
    ) {
        homeScreen(onBookClick = { bookId ->
            navController.navigateToBookDetails(id = bookId)
        })
        favouriteScreen(onBookClick = { bookId ->
            navController.navigateToBookDetails(id = bookId)
        })
    }
}

fun NavController.navigateToBottomBarItem(item: BottomNavigationBarItem) {
    when (item) {
        BottomNavigationBarItem.Home -> navigateToHome(
            navOptions = navOptions {
                launchSingleTop = true
                popUpTo(HomeNavigation)
            }
        )

        BottomNavigationBarItem.Favourite -> navigateToFavourite(
            navOptions = navOptions {
                launchSingleTop = true
                popUpTo(FavouriteNavigation)
            },
        )
    }
}
