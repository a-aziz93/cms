package ui.cdox

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultCDoxComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : CDoxComponent, ComponentContext by componentContext {

}