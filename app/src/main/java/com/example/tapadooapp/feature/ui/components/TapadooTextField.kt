package com.example.tapadooapp.feature.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp

@Composable
fun TapadooTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {

    var isFocused by remember { mutableStateOf(false) }
    val animateOffset by animateDpAsState(
        targetValue = if (isFocused) 2.dp else 8.dp,
        label = "alpha"
    )

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .opaqueShadow(
                offsetY = animateOffset,
                offsetX = (animateOffset - 4.dp).coerceIn(0.dp, 6.dp),
                borderRadius = 4.dp
            )
            .onFocusChanged {
                isFocused = !isFocused
            },
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Black,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedBorderColor = Color.Black
        ),
        leadingIcon = leadingIcon,
        placeholder = { if (placeholder != null) Text(text = placeholder) }
    )
}

@Preview
@Composable
private fun TapadooTextFieldPreview() {
    TapadooTextField(value = "Victor Test", onValueChange = {})
}