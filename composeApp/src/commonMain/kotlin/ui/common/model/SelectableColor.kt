package ui.common.model

import androidx.compose.ui.graphics.Color

data class SelectableColor(
    val color: Color? = null,
    val selectedColor: Color,
)