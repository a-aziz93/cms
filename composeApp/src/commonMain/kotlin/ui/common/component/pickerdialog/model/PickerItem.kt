package ui.common.component.pickerdialog.model

import androidx.compose.runtime.Composable
import ui.common.model.Item

class PickerItem<T : Any>(
    text: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    val value: T? = null,
) : Item(text, icon, badge)