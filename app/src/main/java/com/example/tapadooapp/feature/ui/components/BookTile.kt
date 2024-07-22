package com.example.tapadooapp.feature.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.feature.ui.theme.Gold
import com.example.tapadooapp.core.utils.ComponentShape
import com.example.tapadooapp.feature.domain.model.Book

@Composable
fun BookTile(modifier: Modifier = Modifier, book: Book, onBookClicked: (Book) -> Unit) {

    val blackColor = MaterialTheme.colorScheme.onBackground
    val whiteColor = MaterialTheme.colorScheme.onPrimary
    val borderRadius = 4.dp


    Box(modifier = modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) { onBookClicked(book) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .opaqueShadow(borderRadius = borderRadius)
                .background(color = whiteColor, shape = ComponentShape)
                .border(
                    width = 1.dp, color = blackColor, shape = ComponentShape
                )
                .align(alignment = Alignment.Center)
        ) {
            BookContent(book = book)
        }
        Box(
            modifier = Modifier
                .size(120.dp, 170.dp)
                .opaqueShadow(borderRadius = borderRadius)
                .background(color = whiteColor, shape = ComponentShape)
                .border(
                    width = 1.dp, color = blackColor, shape = ComponentShape
                )
        ) {
            AsyncImage(
                model = book.imageCover,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(ComponentShape)
            )
        }

    }
}

@Composable
private fun BookContent(modifier: Modifier = Modifier, book: Book) {
    Box(
        modifier = modifier
            .padding(start = 4.dp + 120.dp + 0.dp)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Row(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = book.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 22.sp,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier)
                    Text(
                        text = book.author,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                    )
                }

                Log.e("FAV", "BookContent: ${book}")
                val iconColor = if (book.isFav) Gold else Color.Black
                Icon(
                    imageVector = Icons.Rounded.Bookmark,
                    tint = iconColor,
                    contentDescription = "Bookmark Icon",
                )
            }
            HorizontalDivider()
            BookPriceTag(book = book)
        }
    }
}

@Preview
@Composable
private fun BookTilePreview() {
    BookTile(book = sampleData[0], onBookClicked = {})
}