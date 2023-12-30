package ui.component.navigation.model

 sealed interface DeepLink {
     data object None : DeepLink
     class Web(val path: String) : DeepLink
 }