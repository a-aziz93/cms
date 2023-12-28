package ui.cdox

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewCDoxComponent: CDoxComponent, ComponentContext by PreviewComponentContext {

}