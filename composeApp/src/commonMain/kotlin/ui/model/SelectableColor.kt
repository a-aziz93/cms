package ui.model

import androidx.compose.ui.graphics.Color

data class SelectableColor(
    val selectedColor: Color,
    val unselectedColor: Color? = null,
)