package ui.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ui.main.PreviewMainComponent
import ui.root.RootComponent.Child
import ui.root.RootComponent.Child.Main
class PreviewRootComponent : RootComponent {

    override val childStack: Value<ChildStack<*, Child>> =
        MutableValue(
            ChildStack(
                configuration = Unit,
                instance =Main(component = PreviewMainComponent()),
                )
        )

}