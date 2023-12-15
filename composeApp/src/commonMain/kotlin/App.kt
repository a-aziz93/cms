import androidx.compose.material3.*
import androidx.compose.runtime.*
import ui.root.RootComponent
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ui.landing.LandingUi
import ui.main.MainUi

@Composable
fun App(rootComponent: RootComponent) {
    Children(
        stack = rootComponent.childStack,
        animation = stackAnimation(fade()),
        ) {
        when(val child = it.instance) {
            is RootComponent.Child.Landing -> LandingUi(child.component)
            is RootComponent.Child.Main -> MainUi(child.component)
        }
    }
}