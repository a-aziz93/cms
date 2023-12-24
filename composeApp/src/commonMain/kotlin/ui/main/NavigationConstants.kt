package ui.main

import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.strings
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.*
import compose.icons.evaicons.outline.*
import ui.model.NavigationIcon
import ui.model.NavigationItem

@Composable
fun NamedNavigationItems() = mapOf(
    "profile" to NavigationItem(
        title = strings.profile,
        route = MainComponent.Config.Profile,
    ),
)

@Composable
fun IndexedNavigationItems() = listOf(
    NavigationItem(
        title = strings.signIn,
        icon = NavigationIcon(
            selectedIcon = EvaIcons.Fill.LogIn,
            unselectedIcon = EvaIcons.Outline.LogIn,
        ),
        route = MainComponent.Config.SignIn,
    ),
    NavigationItem(
        title = strings.main,
        icon = NavigationIcon(
            selectedIcon = EvaIcons.Fill.Home,
            unselectedIcon = EvaIcons.Outline.Home,
        ),
        route = MainComponent.Config.Home,
    ),
    NavigationItem(
        title = strings.map,
        icon = NavigationIcon(
            selectedIcon = EvaIcons.Fill.Map,
            unselectedIcon = EvaIcons.Outline.Map,
        ),
        route = MainComponent.Config.Map,
    ),
    NavigationItem(
        title = strings.dashboard,
        icon = NavigationIcon(
            selectedIcon = EvaIcons.Fill.Monitor,
            unselectedIcon = EvaIcons.Outline.Monitor,
        ),
        route = MainComponent.Config.Dashboard,
    ),
    NavigationItem(
        title = strings.settings,
        icon = NavigationIcon(
            selectedIcon = EvaIcons.Fill.Settings2,
            unselectedIcon = EvaIcons.Outline.Settings2,
        ),
        route = MainComponent.Config.Settings,
    ),
)

val navConfigOutputMapper = mapOf(
    MainComponent.Config.SignIn to MainComponent.Output.NavigateToSignIn,
    MainComponent.Config.Home to MainComponent.Output.NavigateToHome,
    MainComponent.Config.Map to MainComponent.Output.NavigateToMap,
    MainComponent.Config.Dashboard to MainComponent.Output.NavigateToDashboard,
    MainComponent.Config.Settings to MainComponent.Output.NavigateToSettings
)

