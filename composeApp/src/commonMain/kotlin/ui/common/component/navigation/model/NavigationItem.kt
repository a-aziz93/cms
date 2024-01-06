package ui.common.component.navigation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.common.model.SelectableItem

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
