package ui.dashboard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultDashboardComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
):DashboardComponent, ComponentContext by componentContext {
    
}