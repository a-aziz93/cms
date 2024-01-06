package ui.component.navigation

interface NavigationComponent<T : Any> {
    fun onNavigate(config: T)
}