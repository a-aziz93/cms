package ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

open class Item(
    val text: (@Composable () -> Unit)? = null,
    val icon: (@Composable () -> Unit)? = null,
    val badge: (@Composable () -> Unit)? = null,
)