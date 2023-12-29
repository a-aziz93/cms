package ui.model

import androidx.compose.ui.graphics.Color

data class NavigationItem(
    val title: NavigationTitle? = null,
    val icon: NavigationIcon? = null,
    val color: NavigationColor? = null,
    val badge: NavigationBadge? = null,
    val route: Any?,
)
