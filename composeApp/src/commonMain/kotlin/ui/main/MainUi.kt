package ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.backStack
import core.storage.KeyValueStorage
import core.storage.StorageKeys
import core.util.tabAnimation
import org.koin.compose.koinInject
import ui.dashboard.DashboardUi
import ui.home.HomeUi
import ui.i18n.toCountryAlpha2Code
import ui.main.MainComponent.Child.*
import ui.main.MainComponent.Child.Map
import ui.component.navigation.AdaptiveNavigationLayout
import ui.component.navigation.NavigationLayoutType
import ui.component.navigation.TabRowType
import ui.component.navigation.TabType
import ui.map.MapUi
import ui.component.navigation.model.NavigationItem
import ui.profile.ProfileUi
import ui.reset.ResetUi
import ui.selfsignup.SelfSignUpUi
import ui.settings.SettingsUi
import ui.signin.SignInUi
import ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun MainUi(component: MainComponent) {

    val keyValueStorage = koinInject<KeyValueStorage>()

    val isDarkTheme = keyValueStorage.get(StorageKeys.IS_DARK_THEME.key, Boolean::class, isSystemInDarkTheme())

    var darkTheme by remember { mutableStateOf(isDarkTheme) }

    AppTheme(darkTheme = darkTheme) {

        val lyricist = rememberStrings(
            currentLanguageTag = keyValueStorage.get(
                StorageKeys.LANGUAGE.key,
                String::class,
                Locale.current.toLanguageTag()
            )
        )

        ProvideStrings(lyricist) {
            val currentLngCountryAlpha2Code = lyricist.languageTag.split("-")[0].toCountryAlpha2Code()

            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

            val topAppBarElementColor = if (scrollBehavior.state.collapsedFraction > 0.5) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onPrimary
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                val windowSizeClass = calculateWindowSizeClass()

                val profileNavigationItem = profileNavigationItem()

                val navigationItems = NavigationItems()

                val selectedNavigationItemIndex = remember {
                    mutableIntStateOf(
                        getActiveNavigationItemIndex(
                            navigationItems,
                            component.childStack.active.configuration as MainComponent.Config
                        )
                    )
                }

                var title by remember { mutableStateOf("") }

                AdaptiveNavigationLayout(
                    layoutType = windowSizeClass.widthSizeClass,
                    compactLayoutType = NavigationLayoutType.DRAWER,
                    mediumLayoutType = NavigationLayoutType.BOTTOM_BAR,
                    expandedLayoutType = NavigationLayoutType.TOP_BAR,
                    hasTopAppBar = true,
                    title = title,
                    topAppBarColors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.surface,
                        navigationIconContentColor = topAppBarElementColor,
                        titleContentColor = topAppBarElementColor,
                        actionIconContentColor = topAppBarElementColor,
                    ),
                    TabRowType.TAB_ROW,
                    TabType.TAB,
                    if (component.childStack.backStack.isNotEmpty()) {
                        {
                            component.onOutput(MainComponent.Output.NavigateBack)
                            selectedNavigationItemIndex.intValue = getActiveNavigationItemIndex(
                                navigationItems,
                                component.childStack.active.configuration as MainComponent.Config
                            )
                        }
                    } else null,
                    darkTheme,
                    {
                        darkTheme = !darkTheme
                        keyValueStorage.set(StorageKeys.IS_DARK_THEME.key, darkTheme)
                    },
                    currentLngCountryAlpha2Code,
                    {
                        lyricist.languageTag = it
                        keyValueStorage.set(StorageKeys.LANGUAGE.key, it)
                    },
                    {
                        component.onOutput(MainComponent.Output.NavigateToProfile)
                        selectedNavigationItemIndex.intValue = -1
                        title = profileNavigationItem.title?.value ?: ""
                    },
                    items = navigationItems,
                    selectedItemIndex = selectedNavigationItemIndex.intValue,
                    onItemClick = { index ->
                        selectedNavigationItemIndex.intValue = index
                        component.onOutput(navConfigOutputMapper[navigationItems[selectedNavigationItemIndex.intValue].route!!]!!)
                    }
                ) {
                    Children(component = component)
                }
            }
        }
    }
}

private fun getActiveNavigationItemIndex(navigationItems: List<NavigationItem>, activeConfig: MainComponent.Config) =
    navigationItems.indexOfFirst { it.route == activeConfig }


@Composable
private fun Children(component: MainComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,

        // Workaround for https://issuetracker.google.com/issues/270656235
//        animation = stackAnimation(fade()),
        animation = tabAnimation {
            when (it) {
                is SignUp -> 0
                is SignIn -> 1
                is Reset -> 2
                is Profile -> 3
                is Home -> 4
                is Map -> 5
                is Dashboard -> 6
                is Settings -> 7
            }
        },
    ) {
        when (val child = it.instance) {
            is SignUp -> SelfSignUpUi(child.component)
            is SignIn -> SignInUi(child.component)
            is Reset -> ResetUi(child.component)
            is Profile -> ProfileUi(child.component)
            is Home -> HomeUi(child.component)
            is Map -> MapUi(child.component)
            is Dashboard -> DashboardUi(child.component)
            is Settings -> SettingsUi(child.component)
        }
    }
}




