package ui.cms

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultCMSComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):CMSComponent, ComponentContext by componentContext {

}