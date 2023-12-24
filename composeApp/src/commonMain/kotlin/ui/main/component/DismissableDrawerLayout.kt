package ui.main.component

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
    userHead: @Composable () -> Unit = {},
    userHeadProvided: Boolean = false,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    DismissibleNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            DismissibleDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                if (userHeadProvided) {
                    userHead()
                }
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            onNavigate(index)
                        },
                        icon = {
                            (if (index == selectedItemIndex) item.icon?.selectedIcon else item.icon?.unselectedIcon)?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = item.title
                                )
                            }
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}