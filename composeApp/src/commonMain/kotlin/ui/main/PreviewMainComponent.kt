package ui.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ui.PreviewComponentContext
import ui.home.PreviewHomeComponent
import ui.main.MainComponent.Config
import ui.main.MainComponent.Child
import ui.main.MainComponent.Child.Home

class PreviewMainComponent : MainComponent, ComponentContext by PreviewComponentContext {
    override val childStack: Value<ChildStack<*, Child>> =
        MutableValue(
            ChildStack(
                configuration = Unit,
                instance = Home(component = PreviewHomeComponent()),
            )
        )

    override fun onNavigate(config: Config) {

    }

    override fun onEvent(event: MainComponent.Event) {

    }
}