package ui.component.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import ui.component.navigation.model.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBadgeIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            navigationBadgeColor(
                item,
                selected,
            )
        }
    ) {
        navigationIconColor(
            item,
            selected,
        )
    }
}