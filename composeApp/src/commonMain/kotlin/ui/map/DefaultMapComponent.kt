package ui.map

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultMapComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):MapComponent, ComponentContext by componentContext {

}