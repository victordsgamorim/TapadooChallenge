package com.example.tapadooapp.feature.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tapadooapp.core.sampleData.sampleData
import com.example.tapadooapp.core.utils.ComponentShape
import com.example.tapadooapp.core.utils.ComponentShapeRadius
import com.example.tapadooapp.feature.domain.model.Book
import com.example.tapadooapp.feature.ui.theme.Gold

@Composable
fun BookDetailsBanner(
    book: Book,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onFavouriteClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .aspectRatio(16 / 14f)
            .opaqueShadow(offsetX = 0.dp)
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(
                    bottomStart = ComponentShapeRadius, bottomEnd = ComponentShapeRadius
                ),
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

        TapadooIconButton(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Arrow Back Icon",
            onClick = onBackClick
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = ComponentShapeRadius, bottomEnd = ComponentShapeRadius
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Text(
                book.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text(
                book.author,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
        TapadooIconButton(
            modifier = Modifier
                .offset(y = 25.dp)
                .padding(horizontal = 24.dp)
                .align(alignment = Alignment.BottomEnd),
            imageVector = Icons.Rounded.AutoAwesome,
            tint = if (book.isFav) Gold else MaterialTheme.colorScheme.onBackground,
            contentDescription = "Favourite Icon",
            onClick = onFavouriteClick
        )
    }
}

@Preview
@Composable
private fun BookDetailsBannerPreview() {
    BookDetailsBanner(
        onFavouriteClick = {},
        book = sampleData[0],
        paddingValues = PaddingValues(),
        onBackClick = { }
    )
}