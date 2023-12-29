package ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.backStack
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import core.storage.StorageKeys
import core.util.countries
import core.util.countryAlpha2CodeFlagPathMap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.i18n.supportedLocaleCodes
import ui.i18n.toCountryAlpha2Code
import ui.i18n.toLanguageAlpha2Code
import ui.main.MainComponent
import ui.main.getActiveNavigationItemIndex
import ui.model.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdaptiveNavigationLayout(
    layoutType: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    compactLayoutType: NavigationLayoutType = NavigationLayoutType.DRAWER,
    mediumLayoutType: NavigationLayoutType = NavigationLayoutType.DRAWER,
    expandedLayoutType: NavigationLayoutType = NavigationLayoutType.DRAWER,
    hasTopAppBar: Boolean = true,
    title: String,
    actions: @Composable() (RowScope.() -> Unit) = {},
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit = {},
) {

    val drawerState =
        rememberDrawerState(initialValue = if (layoutType == WindowWidthSizeClass.Compact) DrawerValue.Closed else DrawerValue.Open)

    if (isDrawerLayout(
            layoutType,
            compactLayoutType,
            mediumLayoutType,
            expandedLayoutType
        )
    ) {
        AdaptiveDrawerNavigationLayout(
            layoutType = layoutType,
            drawerState = drawerState,
            head = {
                UserHead(
                    id = "SomeId",
                    firstName = "Aziz",
                    lastName = "Atoev",
                    role = "User",
                    onClick = {
                        onNavigate(-1)
                    }
                )
            },
            items = items,
            selectedItemIndex = selectedItemIndex,
            onNavigate = { index ->
                onNavigate(index)
            }
        ) {
            BarNavigationLayout(
                layoutType,
                compactLayoutType,
                mediumLayoutType,
                expandedLayoutType,
                hasTopAppBar,
                title,
                {},
                actions,
                topAppBarColors,
                items,
                selectedItemIndex,
                onNavigate,
            ) {
                content()
            }
        }
    } else {
        BarNavigationLayout(
            layoutType,
            compactLayoutType,
            mediumLayoutType,
            expandedLayoutType,
            hasTopAppBar,
            title,
            {},
            actions,
            topAppBarColors,
            items,
            selectedItemIndex,
            onNavigate,
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
private fun BarNavigationLayout(
    layoutType: WindowWidthSizeClass,
    compactLayoutType: NavigationLayoutType,
    mediumLayoutType: NavigationLayoutType,
    expandedLayoutType: NavigationLayoutType,
    hasTopAppBar: Boolean = true,
    title: String = "",
    topAppBarColors: TopAppBarColors,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onDrawerClick: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    darkTheme: Boolean,
    onThemeClick: (() -> Unit)? = null,
    language: String,
    onLanguageClick: ((String) -> Unit)? = null,
    onSignOutClick: (() -> Unit)? = null,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    var lpDialogState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (hasTopAppBar) {
                TopAppBar(
                    title = {
                        Text(
                            text = if (selectedItemIndex > -1) items[selectedItemIndex].title ?: "" else title
                        )
                    },
                    navigationIcon = {
                        if (isDrawerLayout(
                                layoutType,
                                compactLayoutType,
                                mediumLayoutType,
                                expandedLayoutType
                            )
                        ) {
                            Row {
                                IconButton(onClick = onDrawerClick) {
                                    Icon(
                                        imageVector = EvaIcons.Outline.Menu2,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        }
                    },
                    actions = {
                        if (onBackClick != null) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = EvaIcons.Outline.ArrowheadLeft,
                                    contentDescription = "Back navigation"
                                )
                            }
                        }
                        if (onLanguageClick != null) {
                            IconButton(
                                onClick = {
                                    lpDialogState = true
                                }) {
                                Image(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(countryAlpha2CodeFlagPathMap[language]!!),
                                    contentDescription = null,
                                )
                            }
                        }
                        if (onThemeClick != null) {
                            IconButton(onClick = onThemeClick) {
                                Icon(
                                    if (darkTheme) EvaIcons.Outline.Sun else EvaIcons.Outline.Moon,
                                    null
                                )
                            }
                        }
                        if (onSignOutClick != null) {
                            IconButton(onClick = onSignOutClick) {
                                Icon(EvaIcons.Outline.LogOut, null)
                            }
                        }
                    },
                    colors = topAppBarColors,
                )
            }
        },
        bottomBar = {
            if (isBottomBarLayout(
                    layoutType,
                    compactLayoutType,
                    mediumLayoutType,
                    expandedLayoutType
                )
            ) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == selectedItemIndex,
                            onClick = {
                                onNavigate(index)
                            },
                            label = {
                                Text(text = item.title ?: "")
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var contentBoxModifier = Modifier.fillMaxSize()
            if (isTopBarLayout(
                    layoutType,
                    compactLayoutType,
                    mediumLayoutType,
                    expandedLayoutType
                )
            ) {
                TabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(innerPadding),
                    selectedTabIndex = selectedItemIndex,
                    divider = {
                        Spacer(modifier = Modifier.height(5.dp))
                    },
                    indicator = { tabPositions ->
                        Box {}
                    },

                    ) {
                    items.forEachIndexed { index, item ->
                        Tab(
                            modifier = if (index == selectedItemIndex) Modifier
                                .clip(RoundedCornerShape(50))
                                .background(
                                    item.color.selectedColor
                                )
                            else Modifier
                                .clip(RoundedCornerShape(50))
                                .background(
                                    item.color.unselectedColor
                                ),
                            selected = index == selectedItemIndex,
                            onClick = {
                                onNavigate(index)
                            },
                            text = {
                                Text(text = item.title ?: "")
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
            } else {
                contentBoxModifier.padding(innerPadding)
            }
            if (lpDialogState) {
                LocalePickerDialog(
                    countries = supportedLocaleCodes.map { lng ->
                        val lngCountryAlpha2Code = lng.toCountryAlpha2Code()
                        countries.find { it.alpha2Code == lngCountryAlpha2Code }!!
                    },
                    defaultSelectedCountry = countries.find { it.alpha2Code == language }!!,
                    pickedCountry = { c ->
                        onLanguageClick!!(c.alpha2Code.toLanguageAlpha2Code())
                        lpDialogState = false
                    },
                    onDismissRequest = {
                        lpDialogState = false
                    },
                )
            }
            Box(modifier = contentBoxModifier) {
                content()
            }
        }
    }
}

private fun isDrawerLayout(
    layoutType: WindowWidthSizeClass,
    compactLayoutType: NavigationLayoutType,
    mediumLayoutType: NavigationLayoutType,
    expandedLayoutType: NavigationLayoutType,
) = (layoutType == WindowWidthSizeClass.Compact && compactLayoutType == NavigationLayoutType.DRAWER) ||
        (layoutType == WindowWidthSizeClass.Medium && mediumLayoutType == NavigationLayoutType.DRAWER) ||
        (layoutType == WindowWidthSizeClass.Expanded && expandedLayoutType == NavigationLayoutType.DRAWER)

private fun isTopBarLayout(
    layoutType: WindowWidthSizeClass,
    compactLayoutType: NavigationLayoutType,
    mediumLayoutType: NavigationLayoutType,
    expandedLayoutType: NavigationLayoutType,
) = (layoutType == WindowWidthSizeClass.Compact && compactLayoutType == NavigationLayoutType.TOP_BAR) ||
        (layoutType == WindowWidthSizeClass.Medium && mediumLayoutType == NavigationLayoutType.TOP_BAR) ||
        (layoutType == WindowWidthSizeClass.Expanded && expandedLayoutType == NavigationLayoutType.TOP_BAR)

private fun isBottomBarLayout(
    layoutType: WindowWidthSizeClass,
    compactLayoutType: NavigationLayoutType,
    mediumLayoutType: NavigationLayoutType,
    expandedLayoutType: NavigationLayoutType,
) = (layoutType == WindowWidthSizeClass.Compact && compactLayoutType == NavigationLayoutType.BOTTOM_BAR) ||
        (layoutType == WindowWidthSizeClass.Medium && mediumLayoutType == NavigationLayoutType.BOTTOM_BAR) ||
        (layoutType == WindowWidthSizeClass.Expanded && expandedLayoutType == NavigationLayoutType.BOTTOM_BAR)

@Composable
fun AdaptiveDrawerNavigationLayout(
    layoutType: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    head: (@Composable () -> Unit)? = null,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onNavigate: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    when (layoutType) {
        WindowWidthSizeClass.Compact -> ModalDrawerLayout(
            modifier,
            drawerState,
            head ?: {},
            items,
            selectedItemIndex,
            onNavigate,
            content
        )

        WindowWidthSizeClass.Medium -> RailDrawerLayout(
            modifier,
            drawerState,
            head,
            items,
            selectedItemIndex,
            onNavigate,
            content
        )

        WindowWidthSizeClass.Expanded -> DismissableDrawerLayout(
            modifier,
            drawerState,
            head ?: {},
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

enum class TabRowType {
    TAB_ROW,
    SCROLLABLE_TAB_ROW,
}

enum class TabType {
    TAB,
    LEADING_ICON_TAB
}