package ui.cdex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultCDexComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : CDexComponent, ComponentContext by componentContext {

}