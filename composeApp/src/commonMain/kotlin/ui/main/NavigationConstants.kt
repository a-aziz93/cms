package ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
        text = {Text(strings.profile)},
        route = MainComponent.Config.Profile,
    )

@Composable
fun NavigationItems() = listOf(
    NavigationItem(
        text =  {Text(strings.signIn)},
        icon = {Icon(EvaIcons.Outline.LogIn,null)},
        selectedIcon = {Icon(EvaIcons.Fill.LogIn,null)},
        route = MainComponent.Config.SignIn,
    ),
    NavigationItem(
        text = {Text(strings.main)},
        icon = {Icon(EvaIcons.Outline.Home,null)},
        selectedIcon = {Icon(EvaIcons.Fill.Home,null)},
        route = MainComponent.Config.Home,
    ),
    NavigationItem(
        text =  {Text(strings.map)},
        icon = {Icon(EvaIcons.Outline.Map,null)},
        selectedIcon = {Icon(EvaIcons.Fill.Map,null)},
        route = MainComponent.Config.Map,
    ),
    NavigationItem(
        text =  {Text(strings.dashboard)},
        icon = {Icon(EvaIcons.Outline.Monitor,null)},
        selectedIcon = {Icon(EvaIcons.Fill.Monitor,null)},
        route = MainComponent.Config.Dashboard,
    ),
    NavigationItem(
        text =  {Text(strings.settings)},
        icon = {Icon(EvaIcons.Outline.Settings2,null)},
        selectedIcon = {Icon(EvaIcons.Fill.Settings2,null)},
        route = MainComponent.Config.Settings,
    ),
)

