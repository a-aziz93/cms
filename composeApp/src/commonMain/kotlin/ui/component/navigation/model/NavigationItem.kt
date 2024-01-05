package ui.component.navigation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ui.model.SelectableColor
import ui.model.SelectableItem

class NavigationItem(
    text: (@Composable () -> Unit)? = null,
    selectedText: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    selectedIcon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    selectedBadge: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier,
    selectedModifier: Modifier = Modifier,
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
