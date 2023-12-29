package ui.model.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationIcon(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val color: NavigationColor? = null,
)
