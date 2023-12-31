package ui.common.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.common.component.navigation.model.NavigationItem

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
                        modifier =
                        item.getModifier(selected)
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = {
                            item.getText(selected)
                        },
                        selected = selected,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            onItemClick(index)
                        },
                        icon = {
                            item.getIcon(selected)
                        },
                        badge = {
                            item.getBadge(selected)
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