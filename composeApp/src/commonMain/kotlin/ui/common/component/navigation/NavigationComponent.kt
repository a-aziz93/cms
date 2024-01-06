package ui.common.component.navigation

interface NavigationComponent<T : Any> {
    fun onNavigate(config: T)
}