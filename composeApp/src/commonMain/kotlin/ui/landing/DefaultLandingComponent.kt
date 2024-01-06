package ui.landing

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ui.root.RootComponent

class DefaultLandingComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    val onNavigateHandler: (RootComponent.Config) -> Unit,
) : LandingComponent, ComponentContext by componentContext {
    override fun onNavigate(config: RootComponent.Config) = onNavigateHandler(config)
}