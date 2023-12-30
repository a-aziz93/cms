package ui.component.navigation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationIcon(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val color: NavigationColor? = null,
)
