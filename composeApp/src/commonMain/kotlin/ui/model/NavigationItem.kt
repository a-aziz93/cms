package ui.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String? = null,
    val icon: NavigationIcon? = null,
    val color: NavigationColor = NavigationColor(Color.Transparent, Color.Transparent),
    val badgeCount: Int? = null,
    val hasNews: Boolean = false,
    val route: Any?,
)
