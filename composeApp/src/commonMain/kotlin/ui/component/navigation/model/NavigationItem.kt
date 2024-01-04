package ui.component.navigation.model

import androidx.compose.ui.graphics.vector.ImageVector
import ui.model.SelectableColor
import ui.model.SelectableItem

class NavigationItem(
    text: String? = null,
    icon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    badge: String? = null,
    textColor: SelectableColor? = null,
    iconColor: SelectableColor? = null,
    badgeColor: SelectableColor? = null,
    backgroundColor: SelectableColor? = null,
    val route: Any?,
) : SelectableItem(
    text,
    icon,
    badge,
    textColor,
    iconColor,
    badgeColor,
    backgroundColor,
)
