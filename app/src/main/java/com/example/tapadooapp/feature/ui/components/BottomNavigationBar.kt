package com.example.tapadooapp.feature.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.feature.ui.theme.TapadooAppTheme

sealed class BottomNavigationBarItem(val icon: ImageVector, val label: String) {

    data object Home : BottomNavigationBarItem(icon = Icons.Rounded.Home, label = "Home")
    data object Favourite : BottomNavigationBarItem(icon = Icons.Rounded.AutoAwesome, label = "Favourite")
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItem: BottomNavigationBarItem,
    items: List<BottomNavigationBarItem>,
    onClick: (BottomNavigationBarItem) -> Unit
) {

    val borderRadius = 16.dp
    val shape = RoundedCornerShape(topStart = borderRadius, topEnd = borderRadius)
    val blackColor = MaterialTheme.colorScheme.onBackground
    val whiteColor = MaterialTheme.colorScheme.onPrimary
    BottomAppBar(
        modifier = modifier
            .border(width = 1.dp, color = blackColor, shape = shape)
            .clip(shape),
        containerColor = whiteColor,
        contentColor = whiteColor
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item == selectedItem,
                onClick = { onClick(item) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = blackColor,
                    selectedIconColor = whiteColor,
                    selectedTextColor = blackColor,
                    unselectedIconColor = blackColor,
//                    unselectedTextColor = unselectedTextBottomAppBarColor
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    TapadooAppTheme {
        Surface {
            BottomNavigationBar(
                selectedItem = BottomNavigationBarItem.Home,
                items = listOf(
                    BottomNavigationBarItem.Home,
                    BottomNavigationBarItem.Favourite
                ),
                onClick = {}
            )
        }
    }
}
