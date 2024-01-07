package ui.common.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

open class SelectableItem(
    text: (@Composable () -> Unit)? = null,
    val selectedText: (@Composable () -> Unit)? = text,
    icon: (@Composable () -> Unit)? = null,
    val selectedIcon: (@Composable () -> Unit)? = icon,
    badge: (@Composable () -> Unit)? = null,
    val selectedBadge: (@Composable () -> Unit)? = badge,
    val modifier: Modifier = Modifier,
    val selectedModifier: Modifier = modifier,
) : Item(text, icon, badge) {
    @Composable
    fun getText(selected: Boolean)=if (selected) selectedText?.invoke() else text?.invoke()

    @Composable
    fun getIcon(selected: Boolean) = if (selected) selectedIcon?.invoke() else icon?.invoke()

    @Composable
    fun getBadge(selected: Boolean) = if (selected) selectedBadge?.invoke() else badge?.invoke()

    fun getModifier(selected: Boolean): Modifier = if (selected) selectedModifier else modifier
}