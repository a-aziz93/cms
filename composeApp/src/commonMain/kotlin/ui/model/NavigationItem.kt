package ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: NavigationIcon?=null,
    val badgeCount: Int? = null,
    val hasNews: Boolean = false,
    val route: Any?,
)
