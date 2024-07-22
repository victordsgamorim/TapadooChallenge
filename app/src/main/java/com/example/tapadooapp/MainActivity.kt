package com.example.tapadooapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tapadooapp.core.navigation.FavouriteNavigation
import com.example.tapadooapp.core.navigation.HomeNavigation
import com.example.tapadooapp.core.navigation.TapadooNavHost
import com.example.tapadooapp.core.navigation.navigateToBottomBarItem
import com.example.tapadooapp.feature.ui.components.BottomNavigationBar
import com.example.tapadooapp.feature.ui.components.BottomNavigationBarItem
import com.example.tapadooapp.feature.ui.theme.TapadooAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            TapadooAppTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopadooApp()
                }
            }
        }
    }
}

@Composable
fun TopadooApp(navController: NavHostController = rememberNavController()) {
    val currentBackStack by navController.currentBackStackEntryAsState()

    val destination = currentBackStack?.destination
    val route = destination?.route

    val selectedItem = remember(destination) {
        when (route) {
            HomeNavigation::class.java.name -> BottomNavigationBarItem.Home
            FavouriteNavigation::class.java.name -> BottomNavigationBarItem.Favourite
            else -> BottomNavigationBarItem.Home
        }
    }

    val bottomAppBarItems = listOf(
        BottomNavigationBarItem.Home, BottomNavigationBarItem.Favourite
    )

    val isShowBackNavigation = when (route) {
        HomeNavigation::class.java.name, FavouriteNavigation::class.java.name -> true
        else -> false
    }

    val isShowAppBar = when (route) {
        HomeNavigation::class.java.name, FavouriteNavigation::class.java.name -> true
        else -> false
    }

    val appBarTitle = when (route) {
        HomeNavigation::class.java.name -> {
            "Tapadoo Book Store"
        }

        FavouriteNavigation::class.java.name -> {
            "Wish List"
        }

        else -> {
            "Book Details"
        }
    }

    TopadooApp(
        selectedItem = selectedItem,
        bottomItems = bottomAppBarItems,
        isShowBackNavigation = isShowBackNavigation,
        isShowAppBar = isShowAppBar,
        onBottomItemClick = {
            navController.navigateToBottomBarItem(it)
        },
        appBarTitle = appBarTitle,
    ) {
        TapadooNavHost(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopadooApp(
    selectedItem: BottomNavigationBarItem,
    bottomItems: List<BottomNavigationBarItem>,
    onBottomItemClick: (BottomNavigationBarItem) -> Unit,
    isShowBackNavigation: Boolean = true,
    isShowAppBar: Boolean = true,
    appBarTitle: String,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        if (isShowAppBar) {
            TopAppBar(
                title = {
                    Text(
                        appBarTitle,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }, bottomBar = {
        if (isShowBackNavigation) {
            BottomNavigationBar(
                selectedItem = selectedItem, items = bottomItems, onClick = onBottomItemClick
            )
        }
    }) {
        val padding = if (!isShowAppBar) {
            PaddingValues(
                bottom = it.calculateBottomPadding(),
                start = it.calculateStartPadding(LocalLayoutDirection.current),
                end = it.calculateEndPadding(LocalLayoutDirection.current)
            )
        } else {
            it
        }
        Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TapadooAppTheme {
        TopadooApp()
    }
}