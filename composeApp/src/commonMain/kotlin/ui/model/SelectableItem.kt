package ui.model

import androidx.compose.ui.graphics.vector.ImageVector

open class SelectableItem(
    text: String? = null,
    icon: ImageVector? = null,
    badge: String? = null,
    val textColor: SelectableColor? = null,
    val iconColor: SelectableColor? = null,
    val badgeColor: SelectableColor? = null,
    val backgroundColor: SelectableColor? = null,
) : Item(text, icon, badge)