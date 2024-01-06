package ui.common.component.navigation

interface EventComponent<T : Any> {
    fun onEvent(event: T)
}