package com.example.tapadooapp.feature.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.feature.ui.components.BookList
import com.example.tapadooapp.feature.ui.components.EmptyBook
import com.example.tapadooapp.feature.ui.components.TapadooLoading
import com.example.tapadooapp.feature.ui.components.TapadooTextField
import com.example.tapadooapp.feature.ui.uistates.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onBookClick: (bookId: Int) -> Unit,
    onSearchValueChanged: (value: String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var searchText by rememberSaveable { mutableStateOf("") }
            TapadooTextField(
                modifier = Modifier.weight(1f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                value = searchText,
                placeholder = "Search book...",
                onValueChange = {
                    searchText = it
                    onSearchValueChanged(searchText)
                }
            )
        }
        when (uiState) {
            is HomeUiState.Loading -> {
                TapadooLoading()
            }

            is HomeUiState.Success -> {
                BookList(books = uiState.books, onBookClick = onBookClick)
            }

            is HomeUiState.Empty -> {
                EmptyBook()
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState.Success(
            sampleData
        ),
        onBookClick = {},
        onSearchValueChanged = {}
    )
}