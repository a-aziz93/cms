package ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ui.component.navigation.model.NavigationItem
import ui.model.SelectableColor

fun navigationModifierColor(
    item: NavigationItem,
    selected: Boolean,
    modifier: Modifier = Modifier,
) =
    if (item.backgroundColor == null || (!selected && item.backgroundColor.unselectedColor == null)) modifier else modifier
        .background(if (selected) item.backgroundColor.selectedColor else item.backgroundColor.unselectedColor!!)

@Composable
fun navigationTextColor(
    item: NavigationItem,
    selected: Boolean,
) {
    if (item.text != null) {
        if (item.textColor == null || (!selected && item.textColor.unselectedColor == null)) {
            Text(text = item.text)
        } else {
            Text(
                text = item.text,
                color = if (selected) item.textColor.selectedColor else item.textColor.unselectedColor!!
            )
        }
    }
}

@Composable
fun navigationIconColor(
    item: NavigationItem,
    selected: Boolean,
) {
    val icon = if (selected) item.icon else item.unselectedIcon
    if (icon != null) {
        if (item.iconColor == null || (!selected && item.iconColor.unselectedColor == null)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) item.iconColor.selectedColor else item.iconColor.unselectedColor!!
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun navigationBadgeColor(
    item: NavigationItem,
    selected: Boolean,
) {
    if (item.badge != null) {
        Badge {
            if (item.badgeColor == null || (!selected && item.badgeColor.unselectedColor == null)) {
                Text(item.badge)
            } else {
                Text(
                    text = item.badge,
                    color = if (selected) item.badgeColor.selectedColor else item.badgeColor.unselectedColor!!
                )
            }
        }
    }
}
