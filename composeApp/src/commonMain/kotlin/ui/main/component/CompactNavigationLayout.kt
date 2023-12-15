package ui.main.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.model.NavigationItem

@Composable
fun CompactNavigationLayout(
    modifier:Modifier=Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    userHead:@Composable ()->Unit={},
    userHeadProvided:Boolean=false,
    items:List<NavigationItem>,
    selectedItemIndex:Int=0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
    ){
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier=modifier,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                if (userHeadProvided){
                    userHead()
                }
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                                },
                        selected = index == selectedItemIndex,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            onNavigate(index)
                                  },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
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