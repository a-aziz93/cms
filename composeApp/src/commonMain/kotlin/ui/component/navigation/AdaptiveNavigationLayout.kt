package ui.component.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import core.util.countries
import core.util.countryAlpha2CodeFlagPathMap
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.component.avatar.Avatar
import ui.component.avatar.InitialsAvatar
import ui.component.locale.LocaleDialog
import ui.i18n.supportedLocaleCodes
import ui.i18n.toCountryAlpha2Code
import ui.i18n.toLanguageAlpha2Code
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
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    tabRowType: TabRowType = TabRowType.TAB_ROW,
    tabType: TabType = TabType.TAB,
    onBackClick: (() -> Unit)? = null,
    darkTheme: Boolean,
    onThemeClick: (() -> Unit)? = null,
    language: String,
    onLanguageClick: ((String) -> Unit)? = null,
    onAvatarClick: (() -> Unit)? = null,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit = {},
) {

    if (isDrawerLayout(
            layoutType,
            compactLayoutType,
            mediumLayoutType,
            expandedLayoutType
        )
    ) {
        val drawerState =
            rememberDrawerState(initialValue = if (layoutType == WindowWidthSizeClass.Compact) DrawerValue.Closed else DrawerValue.Open)

        AdaptiveDrawerNavigationLayout(
            layoutType = layoutType,
            drawerState = drawerState,
            head = {
                if (onAvatarClick != null) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Avatar(
                            firstName = "Aziz",
                            lastName = "Atoev",
                            onClick = onAvatarClick
                        )
                        Text("Admin")
                    }
                }
            },
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemClick = onItemClick
        ) {
            BarNavigationLayout(
                layoutType,
                compactLayoutType,
                mediumLayoutType,
                expandedLayoutType,
                hasTopAppBar,
                title,
                topAppBarColors,
                tabRowType,
                tabType,
                drawerState,
                onBackClick,
                darkTheme,
                onThemeClick,
                language,
                onLanguageClick,
                onAvatarClick,
                items,
                selectedItemIndex,
                onItemClick,
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
            topAppBarColors,
            tabRowType,
            tabType,
            null,
            onBackClick,
            darkTheme,
            onThemeClick,
            language,
            onLanguageClick,
            onAvatarClick,
            items,
            selectedItemIndex,
            onItemClick,
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
    hasTopAppBar: Boolean,
    title: String,
    topAppBarColors: TopAppBarColors,
    tabRowType: TabRowType,
    tabType: TabType,
    drawerState: DrawerState?,
    onBackClick: (() -> Unit)?,
    darkTheme: Boolean,
    onThemeClick: (() -> Unit)?,
    language: String,
    onLanguageClick: ((String) -> Unit)?,
    onAvatarClick: (() -> Unit)?,
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    val scope = rememberCoroutineScope()
    var lpDialogState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (hasTopAppBar) {
                TopAppBar(
                    title = {
                        Text(
                            text = if (selectedItemIndex > -1) items[selectedItemIndex].title?.value ?: "" else title
                        )
                    },
                    navigationIcon = {
                        if (drawerState != null) {
                            Row {
                                IconButton(onClick = {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }) {
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
                                    imageVector = EvaIcons.Outline.ArrowLeft,
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
                        Avatar(
                            firstName = "Aziz",
                            lastName = "Atoev",
                            onClick = onAvatarClick
                        )
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
                        val selected = index == selectedItemIndex
                        NavigationBarItem(
                            modifier = navigationModifierColor(
                                selected = selected,
                                navigationColor = item.color
                            ),
                            selected = selected,
                            onClick = {
                                onItemClick(index)
                            },
                            label = {
                                if (item.title != null) {
                                    navigationTextColor(
                                        item.title.value,
                                        selected,
                                        item.title.color
                                    )
                                }
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgeIcon(
                                    item,
                                    index == selectedItemIndex,
                                )
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
                TabsRow(
                    tabRowType,
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(innerPadding),
                    selectedItemIndex,
                    {
                        Spacer(modifier = Modifier.height(5.dp))
                    },
                    { tabPositions ->
                        if (selectedItemIndex > -1) {
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[selectedItemIndex])
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(8.dp)) // clip modifier not working
                                    .padding(horizontal = 28.dp)
                                    .background(color = Blue)
                            )
                        }
                    },
                ) {
                    Tabs(
                        tabType,
                        Modifier.clip(RoundedCornerShape(8)),
                        items,
                        selectedItemIndex,
                        onClick = onItemClick
                    )
                }
            } else {
                contentBoxModifier = contentBoxModifier.padding(innerPadding)
            }
            if (lpDialogState) {
                LocaleDialog(
                    countries = supportedLocaleCodes.map { lng ->
                        val lngCountryAlpha2Code = lng.toCountryAlpha2Code()
                        countries.find { it.alpha2Code == lngCountryAlpha2Code }!!
                    },
                    defaultSelectedCountry = countries.find { it.alpha2Code == language }!!,
                    pickedCountry = { c ->
                        onLanguageClick!!(c.alpha2Code.toLanguageAlpha2Code())
                        lpDialogState = false
                    },
                    searchHint = "Search ...",
                    onDismissRequest = {
                        lpDialogState = false
                    },
                )
            }
            val scrollState = rememberScrollState()

            Box(modifier = contentBoxModifier.verticalScroll(state = scrollState)) {
                content()
            }
        }
    }
}

@Composable
private fun Tabs(
    type: TabType,
    modifier: Modifier,
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onClick: (Int) -> Unit,
) {
    when (type) {
        TabType.TAB -> items.forEachIndexed { index, item ->
            val selected = index == selectedItemIndex
            Tab(
                modifier = navigationModifierColor(
                    modifier,
                    selected = selected,
                    navigationColor = item.color
                ),
                selected = selected,
                text = {
                    if (item.title != null) {
                        navigationTextColor(
                            item.title.value,
                            selected,
                            item.title.color
                        )
                    }
                },
                icon = {
                    BadgeIcon(
                        item,
                        index == selectedItemIndex
                    )
                },
                onClick = { onClick(index) },
            )
        }

        TabType.LEADING_ICON_TAB -> items.forEachIndexed { index, item ->
            val selected = index == selectedItemIndex
            LeadingIconTab(
                modifier = navigationModifierColor(
                    modifier,
                    selected = selected,
                    navigationColor = item.color
                ),
                selected = selected,
                text = {
                    if (item.title != null) {
                        navigationTextColor(
                            item.title.value,
                            selected,
                            item.title.color
                        )
                    }
                },
                icon = {
                    BadgeIcon(
                        item,
                        index == selectedItemIndex
                    )
                },
                onClick = { onClick(index) }
            )
        }
    }
}

@Composable
private fun TabsRow(
    type: TabRowType,
    modifier: Modifier,
    selectedTabIndex: Int,
    divider: @Composable () -> Unit,
    indicator: @Composable (List<TabPosition>) -> Unit,
    content: @Composable () -> Unit,
) {
    when (type) {
        TabRowType.TAB_ROW -> TabRow(
            modifier = modifier,
            selectedTabIndex = selectedTabIndex,
            divider = divider,
            indicator = indicator,
        ) {
            content()
        }

        TabRowType.SCROLLABLE_TAB_ROW -> ScrollableTabRow(
            modifier = modifier,
            selectedTabIndex = selectedTabIndex,
            divider = divider,
            indicator = indicator,
        ) {
            content()
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
private fun AdaptiveDrawerNavigationLayout(
    layoutType: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    head: (@Composable () -> Unit)? = null,
    items: List<NavigationItem>,
    selectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    when (layoutType) {
        WindowWidthSizeClass.Compact -> ModalDrawerLayout(
            modifier,
            drawerState,
            head ?: {},
            items,
            selectedItemIndex,
            onItemClick,
            content
        )

        WindowWidthSizeClass.Medium -> RailDrawerLayout(
            modifier,
            drawerState,
            head,
            items,
            selectedItemIndex,
            onItemClick,
            content
        )

        WindowWidthSizeClass.Expanded -> DismissableDrawerLayout(
            modifier,
            drawerState,
            head ?: {},
            items,
            selectedItemIndex,
            onItemClick,
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