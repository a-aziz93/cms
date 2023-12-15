package ui.landing

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewLandingComponent: LandingComponent, ComponentContext by PreviewComponentContext {
    override val onOutput: (LandingComponent.Output) -> Unit={}
}