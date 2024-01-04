package ui.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.component.navigation.model.NavigationItem

@Composable
fun RailDrawerLayout(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Open),
    head: (@Composable () -> Unit)? = null,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    Row {
        if (drawerState.isOpen) {
            NavigationRail(
                modifier = modifier,
                header = {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (head != null) {
                        Box(modifier = Modifier.width(80.dp)) {
                            head()
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
                    ) {
                        items.forEachIndexed { index, item ->
                            val selected = selectedItemIndex == index
                            NavigationRailItem(
                                modifier = navigationModifierColor(
                                    item,
                                    selected,
                                ),
                                selected = selected,
                                onClick = {
                                    onItemClick(index)
                                },
                                icon = {
                                    NavigationBadgeIcon(
                                        item = item,
                                        selected = selected
                                    )
                                },
                                label = {
                                    navigationTextColor(
                                        item,
                                        selected,
                                    )
                                },
                            )
                        }
                    }
                },
            ) {

            }
        }
        content()
    }
}

