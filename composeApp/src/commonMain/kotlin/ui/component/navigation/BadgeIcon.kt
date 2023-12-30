package ui.component.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import ui.component.navigation.model.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            if(item.badge!=null) {
                navigationBadgeColor(
                    item.badge,
                    selected,
                )
            }
        }
    ) {
        if (item.icon != null) {
            navigationIconColor(
                if (selected) item.icon.selectedIcon else item.icon.unselectedIcon,
                selected,
                item.icon.color
            )
        }
    }
}