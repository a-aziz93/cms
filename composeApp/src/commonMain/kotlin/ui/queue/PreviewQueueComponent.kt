package ui.queue

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewQueueComponent: QueueComponent, ComponentContext by PreviewComponentContext {

}