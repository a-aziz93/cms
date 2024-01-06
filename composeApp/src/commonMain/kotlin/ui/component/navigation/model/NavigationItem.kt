package ui.component.navigation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ui.model.SelectableColor
import ui.model.SelectableItem

class NavigationItem(
    text: (@Composable () -> Unit)? = null,
    selectedText: (@Composable () -> Unit)? = text,
    icon: (@Composable () -> Unit)? = null,
    selectedIcon: (@Composable () -> Unit)? = icon,
    badge: (@Composable () -> Unit)? = null,
    selectedBadge: (@Composable () -> Unit)? = badge,
    modifier: Modifier = Modifier,
    selectedModifier: Modifier = modifier,
    val route: Any?,
) : SelectableItem(
    text,
    selectedText,
    icon,
    selectedIcon,
    badge,
    selectedBadge,
    modifier,
    selectedModifier,
)
