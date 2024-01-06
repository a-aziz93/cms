package ui.component.pickerdialog.model

import androidx.compose.runtime.Composable
import ui.model.Item

class PickerItem<T : Any>(
    text: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    val value: T? = null,
) : Item(text, icon, badge)