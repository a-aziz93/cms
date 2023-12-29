package ui.main

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import co.touchlab.kermit.Logger
import com.arkivanov.decompose.extensions.compose.stack.Children
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import core.storage.StorageKeys
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.home.HomeUi
import ui.main.component.AdaptiveNavigationLayout
import ui.theme.AppTheme
import ui.main.MainComponent.Child.SignUp
import ui.main.MainComponent.Child.SignIn
import ui.main.MainComponent.Child.Reset
import ui.main.MainComponent.Child.Profile
import ui.main.MainComponent.Child.Home
import ui.main.MainComponent.Child.Map
import ui.main.MainComponent.Child.Dashboard
import ui.main.MainComponent.Child.Settings
import ui.map.MapUi
import ui.reset.ResetUi
import ui.settings.SettingsUi
import ui.signin.SignInUi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.backStack
import core.storage.KeyValueStorage
import ui.main.component.LocalePickerDialog
import ui.main.component.UserHead
import ui.model.NavigationItem
import ui.profile.ProfileUi
import core.util.countries
import core.util.countryAlpha2CodeFlagPathMap
import core.util.tabAnimation
import org.koin.compose.koinInject
import ui.dashboard.DashboardUi
import ui.i18n.*
import ui.main.component.NavigationLayoutType
import ui.selfsignup.SelfSignUpUi

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
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

                val namedNavigationItems = NamedNavigationItems()
                val indexedNavigationItems = IndexedNavigationItems()

                val selectedNavigationItemIndex = remember {
                    mutableIntStateOf(
                        getActiveNavigationItemIndex(
                            indexedNavigationItems,
                            component.childStack.active.configuration as MainComponent.Config
                        )
                    )
                }

                var title by remember { mutableStateOf("") }

                AdaptiveNavigationLayout(
                    layoutType = windowSizeClass.widthSizeClass,
                    compactLayoutType = NavigationLayoutType.DRAWER,
                    mediumLayoutType = NavigationLayoutType.DRAWER,
                    expandedLayoutType = NavigationLayoutType.DRAWER,
//                    userHead = {
//                        UserHead(
//                            id = "SomeId",
//                            firstName = "Aziz",
//                            lastName = "Atoev",
//                            role = "User",
//                            onClick = {
//                                component.onOutput(MainComponent.Output.NavigateToProfile)
//                                title = namedNavigationItems["profile"]!!.title
//                                selectedNavigationItemIndex.intValue = -1
//                            }
//                        )
//                    },
                    hasTopAppBar = true,
                    title = title,
                    actions = {},
                    topAppBarColors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.surface,
                        navigationIconContentColor = topAppBarElementColor,
                        titleContentColor = topAppBarElementColor,
                        actionIconContentColor = topAppBarElementColor,
                    ),
                    items = indexedNavigationItems,
                    selectedItemIndex = selectedNavigationItemIndex.intValue,
                    onNavigate = { index ->
                        selectedNavigationItemIndex.intValue = index
                        component.onOutput(navConfigOutputMapper[indexedNavigationItems[selectedNavigationItemIndex.intValue].route!!]!!)
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




