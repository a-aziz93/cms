package ui.map

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewMapComponent: MapComponent, ComponentContext by PreviewComponentContext {

}