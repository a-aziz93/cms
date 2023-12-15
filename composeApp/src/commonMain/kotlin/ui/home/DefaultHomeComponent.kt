package ui.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultHomeComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):HomeComponent, ComponentContext by componentContext {
    
}