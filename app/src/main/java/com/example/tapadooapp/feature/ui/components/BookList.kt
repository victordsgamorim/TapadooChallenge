package com.example.tapadooapp.feature.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.feature.domain.model.Book

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onBookClick: (bookId: Int) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(books) { book ->
            BookTile(
                book = book,
                onBookClicked = {
                    onBookClick(book.id)
                }
            )
        }
        item { Spacer(Modifier) }
    }
}

@Preview
@Composable
private fun BookListPreview() {
    BookList(books = sampleData, onBookClick = {})
}