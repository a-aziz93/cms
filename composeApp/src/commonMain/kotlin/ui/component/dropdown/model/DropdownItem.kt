package ui.component.dropdown.model

import androidx.compose.runtime.Composable
import ui.model.Item

class DropdownItem(
    text: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    val trailingIcon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    val trailingBadge: (@Composable () -> Unit)? = null,
) : Item(text, icon, badge)