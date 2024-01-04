package ui.component.contextmenu.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ContextMenuItem(
    val text: String? = null,
    val icon: ImageVector? = null,
    val badge: String? = null,
)