package ui.queue

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class DefaultQueueComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : QueueComponent, ComponentContext by componentContext {

}