package ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

open class SelectableItem(
    text: (@Composable () -> Unit)? = null,
    private val selectedText: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    private val selectedIcon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    private val selectedBadge: (@Composable () -> Unit)? = null,
    val modifier: Modifier = Modifier,
    private val selectedModifier: Modifier = Modifier,
) : Item(text, icon, badge) {
    fun getText(selected: Boolean): @Composable () -> Unit = (if (selected) selectedText else text) ?: {}

    fun getIcon(selected: Boolean): @Composable () -> Unit = (if (selected) selectedIcon else icon) ?: {}
    fun getBadge(selected: Boolean): @Composable () -> Unit = (if (selected) selectedBadge else badge) ?: {}
    fun getModifier(selected: Boolean): Modifier = if (selected) selectedModifier else modifier
}