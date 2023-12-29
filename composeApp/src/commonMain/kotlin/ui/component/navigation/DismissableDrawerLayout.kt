package ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.model.NavigationItem

@Composable
fun DismissableDrawerLayout(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Open),
    head: @Composable () -> Unit = {},
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    DismissibleNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            DismissibleDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                head()
                items.forEachIndexed { index, item ->
                    val selected = index == selectedItemIndex
                    NavigationDrawerItem(
                        modifier = navigationModifierColor(
                            selected = selected,
                            navigationColor = item.color
                        )
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = {
                            if(item.title!=null) {
                                navigationTextColor(
                                    item.title.value,
                                    selected,
                                    item.title.color
                                )
                            }
                        },
                        selected = selected,
                        onClick = {
                            onItemClick(index)
                        },
                        icon = {
                            if (item.icon != null) {
                                navigationIconColor(
                                    if (selected) item.icon.selectedIcon else item.icon.unselectedIcon,
                                    selected,
                                    item.icon.color
                                )
                            }
                        },
                        badge = {
                            if(item.badge!=null) {
                                navigationBadgeColor(
                                    item.badge,
                                    selected,
                                )
                            }
                        },
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}