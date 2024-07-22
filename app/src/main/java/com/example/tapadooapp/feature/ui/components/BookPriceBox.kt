package com.example.tapadooapp.feature.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.core.utils.ComponentShape
import com.example.tapadooapp.core.utils.currency
import com.example.tapadooapp.feature.domain.model.Book

@Composable
fun BookPriceTag(
    modifier: Modifier = Modifier,
    book: Book,
    style: TextStyle? = null
) {
    Text(
        modifier = modifier
            .padding(top = 4.dp)
            .opaqueShadow(offsetX = 2.dp, offsetY = 2.dp)
            .background(color = Color.White, shape = ComponentShape)
            .border(BorderStroke(1.dp, Color.Black), shape = ComponentShape)
            .padding(8.dp),
        text = book.price.currency(book.currencyCode),
        style = style ?: MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Bold
        )
    )
}

@Preview
@Composable
private fun BookPriceTagPreview() {
    BookPriceTag(book = sampleData[0])
}