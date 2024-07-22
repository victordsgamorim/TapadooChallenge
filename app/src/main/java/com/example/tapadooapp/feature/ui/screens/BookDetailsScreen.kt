package com.example.tapadooapp.feature.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.feature.domain.model.Book
import com.example.tapadooapp.feature.ui.components.BookDetailsBanner
import com.example.tapadooapp.feature.ui.components.BookPriceTag
import com.example.tapadooapp.feature.ui.components.TapadooLoading
import com.example.tapadooapp.feature.ui.uistates.BookDetailsUiState


@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUiState,
    onFavouriteClick: (Book) -> Unit,
    onBackClient: () -> Unit,
    modifier: Modifier = Modifier,
) {

    when (uiState) {
        is BookDetailsUiState.Loading -> {
            TapadooLoading()
        }

        is BookDetailsUiState.Success -> {
            Scaffold { paddingValues ->
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BookDetailsBanner(
                        book = uiState.book,
                        paddingValues = paddingValues,
                        onFavouriteClick = { onFavouriteClick(uiState.book) },
                        onBackClick = onBackClient
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(imageVector = Icons.Default.QrCode2, contentDescription = "")
                                Text(
                                    text = "ISBN: ${uiState.book.isbn}",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }

                        item {
                            BookPriceTag(
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                book = uiState.book
                            )
                        }
                        item {
                            Text(
                                text = LoremIpsum(250).values.first(),
                                textAlign = TextAlign.Justify,
                            )
                        }
                        item {
                            Spacer(Modifier)
                        }
                    }

                }
            }
        }

        is BookDetailsUiState.Empty -> {}
    }

}

@Preview(showSystemUi = true)
@Composable
private fun BookDetailsScreenPreview() {
    BookDetailsScreen(
        uiState = BookDetailsUiState.Success(sampleData[0]),
        onFavouriteClick = {},
        onBackClient = {},
    )
}