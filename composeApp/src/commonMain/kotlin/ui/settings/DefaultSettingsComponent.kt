package ui.settings

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):SettingsComponent, ComponentContext by componentContext {
    
}