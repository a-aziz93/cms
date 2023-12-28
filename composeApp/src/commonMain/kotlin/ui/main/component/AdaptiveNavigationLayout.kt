package ui.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.model.NavigationItem

@Composable
fun AdaptiveNavigationLayout(
    compactLayoutType: NavigationLayoutType= NavigationLayoutType.DRAWER,
    mediumLayoutType: NavigationLayoutType= NavigationLayoutType.DRAWER,
    expandedLayoutType: NavigationLayoutType= NavigationLayoutType.DRAWER,
    navigationItems: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable (PaddingValues) -> Unit = {},
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BarNavigationLayout(
    layoutType: NavigationLayoutType,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    navigationItems: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable (PaddingValues) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                colors = topAppBarColors,
            )
        },
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItemIndex,
                        onClick = {
                            onNavigate(index)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
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
                                (if (index == selectedItemIndex) {
                                    item.icon?.selectedIcon
                                } else item.icon?.unselectedIcon)?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedItemIndex) {
                navigationItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedItemIndex,
                        onClick = {
                            onNavigate(index)
                        },
                        text = {
                            Text(text = item.title)
                        },
                        icon = {

                            (if (index == selectedItemIndex)
                                item.icon?.selectedIcon else item.icon?.unselectedIcon)?.let { icon ->
                                Icon(
                                    imageVector = icon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                content(it)
            }
        }
    }
}

@Composable
fun AdaptiveDrawerNavigationLayout(
    layoutType: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    userHead: @Composable () -> Unit = {},
    userHeadProvided: Boolean = false,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    when (layoutType) {
        WindowWidthSizeClass.Compact -> ModalDrawerLayout(
            modifier,
            drawerState,
            userHead,
            userHeadProvided,
            items,
            selectedItemIndex,
            onNavigate,
            content
        )

        WindowWidthSizeClass.Medium -> RailDrawerLayout(
            modifier,
            drawerState,
            userHead,
            userHeadProvided,
            items,
            selectedItemIndex,
            onNavigate,
            content
        )

        WindowWidthSizeClass.Expanded -> DismissableDrawerLayout(
            modifier,
            drawerState,
            userHead,
            userHeadProvided,
            items,
            selectedItemIndex,
            onNavigate,
            content
        )
    }
}

enum class NavigationLayoutType {
    TOP_BAR,
    DRAWER,
    BOTTOM_BAR,
}