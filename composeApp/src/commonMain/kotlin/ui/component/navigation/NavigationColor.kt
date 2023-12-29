package ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ui.model.NavigationBadge
import ui.model.NavigationColor
import ui.model.NavigationItem

fun navigationModifierColor(
    modifier: Modifier = Modifier,
    selected: Boolean,
    navigationColor: NavigationColor? = null,
) =
    if (navigationColor == null) modifier else modifier.background(if (selected) navigationColor.selectedColor else navigationColor.unselectedColor)

@Composable
fun navigationTextColor(
    text: String,
    selected: Boolean,
    navigationColor: NavigationColor? = null,
) = if (navigationColor == null) Text(text = text) else Text(
    text = text,
    color = if (selected) navigationColor.selectedColor else navigationColor.unselectedColor
)

@Composable
fun navigationIconColor(
    imageVector: ImageVector,
    selected: Boolean,
    navigationColor: NavigationColor? = null,
) = if (navigationColor == null) Icon(
    imageVector = imageVector,
    contentDescription = null,
) else Icon(
    imageVector = imageVector,
    contentDescription = null,
    tint = if (selected) navigationColor.selectedColor else navigationColor.unselectedColor
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun navigationBadgeColor(
    badge: NavigationBadge,
    selected: Boolean,
) =
    Badge {
        if (badge.count != null) {
            navigationTextColor(
                badge.count.toString(), selected, badge.color
            )
        }
    }
