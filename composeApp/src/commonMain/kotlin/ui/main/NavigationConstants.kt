package ui.main

import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.strings
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.*
import compose.icons.evaicons.outline.*
import ui.component.navigation.model.NavigationItem

@Composable
fun profileNavigationItem() = NavigationItem(
        text = strings.profile,
        route = MainComponent.Config.Profile,
    )

@Composable
fun NavigationItems() = listOf(
    NavigationItem(
        text =  strings.signIn,
        icon =  EvaIcons.Fill.LogIn,
            unselectedIcon = EvaIcons.Outline.LogIn,
        route = MainComponent.Config.SignIn,
    ),
    NavigationItem(
        text = strings.main,
        icon = EvaIcons.Fill.Home,
            unselectedIcon = EvaIcons.Outline.Home,
        route = MainComponent.Config.Home,
    ),
    NavigationItem(
        text =  strings.map,
        icon =  EvaIcons.Fill.Map,
            unselectedIcon = EvaIcons.Outline.Map,
        route = MainComponent.Config.Map,
    ),
    NavigationItem(
        text =  strings.dashboard,
        icon  = EvaIcons.Fill.Monitor,
            unselectedIcon = EvaIcons.Outline.Monitor,

        route = MainComponent.Config.Dashboard,
    ),
    NavigationItem(
        text =  strings.settings,
        icon = EvaIcons.Fill.Settings2,
            unselectedIcon = EvaIcons.Outline.Settings2,
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

