package ui.main

import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.strings
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.*
import compose.icons.evaicons.outline.*
import ui.model.NavigationItem

@Composable
fun NavigationItems()=listOf(
    NavigationItem(title= strings.signin,
                   selectedIcon = EvaIcons.Fill.LogIn,
                   unselectedIcon = EvaIcons.Outline.LogIn,
                   route=MainComponent.Config.SignIn,
        ),
    NavigationItem(title= strings.main,
                   selectedIcon = EvaIcons.Fill.Home,
                   unselectedIcon = EvaIcons.Outline.Home,
                   route=MainComponent.Config.Home,
        ),
    NavigationItem(title= strings.map,
                   selectedIcon = EvaIcons.Fill.Map,
                   unselectedIcon = EvaIcons.Outline.Map,
                   route= MainComponent.Config.Map,
        ),
    NavigationItem(title= strings.dashboard,
                   selectedIcon = EvaIcons.Fill.Monitor,
                   unselectedIcon = EvaIcons.Outline.Monitor,
                   route = MainComponent.Config.Dashboard,
        ),
    NavigationItem(title= strings.settings,
                   selectedIcon = EvaIcons.Fill.Settings2,
                   unselectedIcon = EvaIcons.Outline.Settings2,
                   route= MainComponent.Config.Settings,
        ),
    )

val navConfigOutputMapper= mapOf(
    MainComponent.Config.SignIn to MainComponent.Output.NavigateToSignIn,
    MainComponent.Config.Home to MainComponent.Output.NavigateToHome,
    MainComponent.Config.Map to MainComponent.Output.NavigateToMap,
    MainComponent.Config.Map to MainComponent.Output.NavigateToDashboard,
    MainComponent.Config.Settings to MainComponent.Output.NavigateToSettings
)