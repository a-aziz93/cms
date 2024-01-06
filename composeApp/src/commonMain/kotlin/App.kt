import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import org.koin.compose.KoinContext
import ui.landing.LandingUi
import ui.main.MainUi
import ui.root.RootComponent
import ui.root.RootUi

@Composable
fun App(component: RootComponent) = RootUi(component)