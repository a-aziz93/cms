package ui.component.dropdown.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DropDownItem(
    val text: String? = null,
    val icon: ImageVector? = null,
    val badge: String? = null,
)