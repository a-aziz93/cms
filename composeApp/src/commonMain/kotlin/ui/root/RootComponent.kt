package ui.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ui.main.MainComponent
import kotlinx.serialization.Serializable
import ui.component.navigation.NavigationChild
import ui.component.navigation.NavigationConfig
import ui.landing.LandingComponent
import kotlin.reflect.KClass

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child : NavigationChild() {
        class Landing(val component: LandingComponent) : Child()
        class Main(val component: MainComponent) : Child()


    }

    @Serializable
    sealed class Config : NavigationConfig() {
        @Serializable
        data object Landing : Config()

        @Serializable
        data object Main : Config()
    }
}