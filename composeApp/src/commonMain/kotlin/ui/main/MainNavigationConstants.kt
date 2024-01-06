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
    text = { Text(strings.profile) },
    route = MainComponent.Config.Profile,
)

@Composable
fun NavigationItems() = listOf(
    NavigationItem(
        text = { Text(strings.signIn) },
        icon = { Icon(EvaIcons.Outline.PersonAdd, null) },
        selectedIcon = { Icon(EvaIcons.Fill.PersonAdd, null) },
        route = MainComponent.Config.SelfSignUp,
    ),
    NavigationItem(
        text = { Text(strings.signIn) },
        icon = { Icon(EvaIcons.Outline.LogIn, null) },
        selectedIcon = { Icon(EvaIcons.Fill.LogIn, null) },
        route = MainComponent.Config.SignIn,
    ),
    NavigationItem(
        text = { Text(strings.main) },
        icon = { Icon(EvaIcons.Outline.Home, null) },
        selectedIcon = { Icon(EvaIcons.Fill.Home, null) },
        route = MainComponent.Config.Home,
    ),
    NavigationItem(
        text = { Text(strings.map) },
        icon = { Icon(EvaIcons.Outline.Map, null) },
        selectedIcon = { Icon(EvaIcons.Fill.Map, null) },
        route = MainComponent.Config.Map,
    ),
    NavigationItem(
        text = { Text(strings.dashboard) },
        icon = { Icon(EvaIcons.Outline.DiagonalArrowRightUp, null) },
        selectedIcon = { Icon(EvaIcons.Fill.DiagonalArrowRightUp, null) },
        route = MainComponent.Config.Dashboard,
    ),
    NavigationItem(
        text = { Text(strings.settings) },
        icon = { Icon(EvaIcons.Outline.Settings2, null) },
        selectedIcon = { Icon(EvaIcons.Fill.Settings2, null) },
        route = MainComponent.Config.Settings,
    ),
    NavigationItem(
        text = { Text(strings.cms) },
        icon = { Icon(EvaIcons.Outline.Monitor, null) },
        selectedIcon = { Icon(EvaIcons.Fill.Monitor, null) },
        route = MainComponent.Config.CMS,
    ),
    NavigationItem(
        text = { Text(strings.queue) },
        icon = { Icon(EvaIcons.Outline.ArrowRight, null) },
        selectedIcon = { Icon(EvaIcons.Fill.ArrowRight, null) },
        route = MainComponent.Config.Queue,
    ),
    NavigationItem(
        text = { Text(strings.cdox) },
        icon = { Icon(EvaIcons.Outline.Book, null) },
        selectedIcon = { Icon(EvaIcons.Fill.Book, null) },
        route = MainComponent.Config.CDox,
    ),
    NavigationItem(
        text = { Text(strings.cdex) },
        icon = { Icon(EvaIcons.Outline.BookOpen, null) },
        selectedIcon = { Icon(EvaIcons.Fill.BookOpen, null) },
        route = MainComponent.Config.CDex,
    ),
)

