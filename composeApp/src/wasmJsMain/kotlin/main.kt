import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.DefaultWebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import ui.dynamicfeatures.dynamicfeature.DefaultFeatureInstaller
import ui.root.DefaultRootComponent
import com.arkivanov.sample.shared.root.RootContent
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    initKoin()

    val lifecycle = LifecycleRegistry()

    val root =
        DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            featureInstaller = DefaultFeatureInstaller,
            deepLink = DefaultRootComponent.DeepLink.Web(path = window.location.pathname),
            webHistoryController = DefaultWebHistoryController(),
            )

    lifecycle.attachToDocument()
    
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
}