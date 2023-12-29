package ui.main.component

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.model.NavigationItem

@Composable
fun ModalDrawerLayout(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    head: @Composable () -> Unit = {},
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                head()
                items.forEachIndexed { index, item ->
                    val selected = index == selectedItemIndex
                    NavigationDrawerItem(
                        modifier = (if (selected) Modifier
                            .background(item.color.selectedColor)
                        else Modifier
                            .background(item.color.unselectedColor))
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = {
                            Text(text = item.title ?: "")
                        },
                        selected = selected,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            onItemClick(index)
                        },
                        icon = {
                            (if (selected) item.icon?.selectedIcon
                            else item.icon?.unselectedIcon)?.let {
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

                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}