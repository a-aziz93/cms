package ui.main.component

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import ui.model.NavigationItem

@Composable
fun AdaptiveNavigationLayout(
    layoutType:WindowWidthSizeClass= WindowWidthSizeClass.Compact,
    modifier:Modifier=Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    userHead:@Composable ()->Unit={},
    userHeadProvided:Boolean=false,
    items:List<NavigationItem>,
    selectedItemIndex:Int=0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
    ){
    when(layoutType){
        WindowWidthSizeClass.Compact-> CompactNavigationLayout(modifier,drawerState,userHead,userHeadProvided,items,selectedItemIndex,onNavigate, content)
        WindowWidthSizeClass.Medium-> MediumNavigationLayout(modifier,drawerState,userHead,userHeadProvided,items,selectedItemIndex,onNavigate, content)
        WindowWidthSizeClass.Expanded-> ExpandedNavigationLayout(modifier,drawerState,userHead,userHeadProvided,items,selectedItemIndex,onNavigate, content)
    }
}