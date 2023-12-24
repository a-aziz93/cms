package ui.main.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.model.NavigationItem

@Composable
fun RailDrawerLayout(
    modifier:Modifier=Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Open),
    userHead:@Composable ()->Unit={},
    userHeadProvided:Boolean=false,
    items:List<NavigationItem>,
    selectedItemIndex:Int=0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
    ){
    Row {
        if(drawerState.isOpen){
            NavigationRail(
                modifier=modifier,
                header = {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (userHeadProvided){
                        Box(modifier=Modifier.width(80.dp)){
                            userHead()
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationRailItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    onNavigate(index)
                                          },
                                icon = {
                                    NavigationIcon(
                                        item = item,
                                        selected = selectedItemIndex == index
                                    )
                                       },
                                label = {
                                    Text(text = item.title)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge {
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        (if (selected) item.icon?.selectedIcon else item.icon?.unselectedIcon)?.let {
            Icon(
                imageVector = it,
                contentDescription = item.title
            )
        }
    }
}