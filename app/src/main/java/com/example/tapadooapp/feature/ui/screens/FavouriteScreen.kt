package com.example.tapadooapp.feature.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.feature.ui.components.BookList
import com.example.tapadooapp.feature.ui.components.EmptyBook
import com.example.tapadooapp.feature.ui.components.TapadooLoading
import com.example.tapadooapp.feature.ui.uistates.FavouriteUiState

@Composable
fun FavouriteScreen(
    uiState: FavouriteUiState,
    onBookClick: (bookId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (uiState) {
            is FavouriteUiState.Loading -> {
                TapadooLoading()
            }

            is FavouriteUiState.Success -> {
                BookList(
                    modifier = modifier.padding(16.dp),
                    books = uiState.books, onBookClick = onBookClick
                )
            }

            is FavouriteUiState.Empty -> {
                EmptyBook(modifier.align(Alignment.Center))
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(
        uiState = FavouriteUiState.Empty,
        onBookClick = {}
    )
}