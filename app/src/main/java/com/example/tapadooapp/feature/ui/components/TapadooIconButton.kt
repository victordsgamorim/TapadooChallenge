package com.example.tapadooapp.feature.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapadooapp.core.utils.ComponentShape

@Composable
fun TapadooIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    tint: Color = LocalContentColor.current,
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .opaqueShadow()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = ComponentShape
            )
            .border(BorderStroke(1.dp, Color.Black), shape = ComponentShape),
        onClick = onClick
    ) {
        Icon(imageVector, contentDescription = contentDescription, tint = tint)
    }
}

@Preview
@Composable
private fun TapadooIconButtonPreview() {
    TapadooIconButton(
        imageVector = Icons.Filled.ShoppingCart,
        contentDescription = "ShoppingCart Icon",
        onClick = {

        }
    )
}