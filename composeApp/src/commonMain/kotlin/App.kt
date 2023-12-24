
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import org.koin.compose.KoinContext
import ui.landing.LandingUi
import ui.main.MainUi
import ui.root.RootComponent

@Composable
fun App(rootComponent: RootComponent) {
    // Set current Koin instance to Compose context
    KoinContext {
        Children(
            stack = rootComponent.childStack,
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Landing -> LandingUi(child.component)
                is RootComponent.Child.Main -> MainUi(child.component)
            }
        }
    }
}