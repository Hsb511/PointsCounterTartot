package com.team23.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@ExperimentalMaterialApi
@Composable
fun FormChip(
    text: String,
    colorState: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Chip(
        onClick = { onClick() },
        colors = ChipDefaults.chipColors(
            backgroundColor = when {
                colorState.value -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity)
            }
        )
    ) {
        Text(
            text = text,
            color = when {
                colorState.value -> MaterialTheme.colors.onPrimary
                else -> MaterialTheme.colors.onSurface
            }
        )
    }
}