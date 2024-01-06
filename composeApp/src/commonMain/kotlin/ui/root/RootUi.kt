package ui.root

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import org.koin.compose.KoinContext
import ui.landing.LandingUi
import ui.main.MainUi

@Composable
internal fun RootUi(component: RootComponent) {
   // Set current Koin instance to Compose context
   KoinContext {
      Children(
         stack = component.childStack,
         animation = stackAnimation(fade()),
      ) {
         when (val child = it.instance) {
            is RootComponent.Child.Landing -> LandingUi(child.component)
            is RootComponent.Child.Main -> MainUi(child.component)
         }
      }
   }
}