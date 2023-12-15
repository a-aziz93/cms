package ui.landing

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ui.landing.LandingComponent.Output

class DefaultLandingComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    override val onOutput: (Output) -> Unit
):LandingComponent, ComponentContext by componentContext {
    
}