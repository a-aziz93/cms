package ui.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultProfileComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):ProfileComponent, ComponentContext by componentContext {

}