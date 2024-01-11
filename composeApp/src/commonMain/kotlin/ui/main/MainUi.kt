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
import core.i18n.toCountryAlpha2Code
import core.data.storage.KeyValueStorage
import core.data.storage.StorageKeys
import core.data.storage.model.LoginInfo
import core.util.tabAnimation
import org.koin.compose.koinInject
import ui.adminsignup.AdminSignUpUi
import ui.cdex.CDexUi
import ui.cdox.CDoxUi
import ui.cms.CMSUi
import ui.common.component.navigation.AdaptiveNavigationLayout
import ui.common.component.navigation.NavigationLayoutType
import ui.common.component.navigation.TabRowType
import ui.common.component.navigation.TabType
import ui.common.component.navigation.model.NavigationItem
import ui.dashboard.DashboardUi
import ui.home.HomeUi
import ui.main.MainComponent.Child.*
import ui.main.MainComponent.Child.Map
import ui.map.MapUi
import ui.profile.ProfileUi
import ui.queue.QueueUi
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

                val activeNavigationItemIndex = currentNavigationItemIndex(
                    navigationItems,
                    component.childStack.active.configuration as MainComponent.Config
                )

                var selectedNavigationItem by remember {
                    mutableStateOf(
                        IndexedValue(
                            activeNavigationItemIndex,
                            navigationItems[activeNavigationItemIndex]
                        )
                    )
                }

                AdaptiveNavigationLayout(
                    layoutType = windowSizeClass.widthSizeClass,
                    compactLayoutType = NavigationLayoutType.DRAWER,
                    mediumLayoutType = NavigationLayoutType.BOTTOM_BAR,
                    expandedLayoutType = NavigationLayoutType.TOP_BAR,
                    hasTopAppBar = true,
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
                            component.onEvent(MainComponent.Event.NAVIGATE_BACK)
                            val ai = currentNavigationItemIndex(
                                navigationItems,
                                component.childStack.active.configuration as MainComponent.Config
                            )
                            selectedNavigationItem = IndexedValue(ai, navigationItems[ai])
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
                    loginInfo = LoginInfo("Rick Sanches", "some", "User"),
                    logOutLabel = strings.signOut,
                    onLogOut = {

                    },
                    {
                        selectedNavigationItem = IndexedValue(-1, profileNavigationItem)
                        component.onNavigate(selectedNavigationItem.value.route as MainComponent.Config)
                    },
                    items = navigationItems,
                    selectedItem = selectedNavigationItem,
                    onItemClick = { index ->
                        selectedNavigationItem = IndexedValue(index, navigationItems[index])
                        component.onNavigate(selectedNavigationItem.value.route as MainComponent.Config)
                    }
                ) {
                    Children(
                        stack = component.childStack,
                        // Workaround for https://issuetracker.google.com/issues/270656235
//        animation = stackAnimation(fade()),
                        animation = tabAnimation {
                            it.indexOfChild()
                        },
                    ) {
                        when (val child = it.instance) {
                            is SelfSignUp -> SelfSignUpUi(child.component)
                            is AdminSignUp -> AdminSignUpUi(child.component)
                            is SignIn -> SignInUi(child.component)
                            is Reset -> ResetUi(child.component)
                            is Profile -> ProfileUi(child.component)
                            is Home -> HomeUi(child.component)
                            is Map -> MapUi(child.component)
                            is CMS -> CMSUi(child.component)
                            is Queue -> QueueUi(child.component)
                            is CDox -> CDoxUi(child.component)
                            is CDex -> CDexUi(child.component)
                            is Dashboard -> DashboardUi(child.component)
                            is Settings -> SettingsUi(child.component)
                        }
                    }
                }
            }
        }
    }
}

private fun currentNavigationItemIndex(navigationItems: List<NavigationItem>, currentRoute: MainComponent.Config) =
    navigationItems.indexOfFirst { it.route == currentRoute }




