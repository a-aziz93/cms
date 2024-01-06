package ui.landing

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext
import ui.root.RootComponent

class PreviewLandingComponent: LandingComponent, ComponentContext by PreviewComponentContext {
    override fun onNavigate(config: RootComponent.Config) {

    }
}