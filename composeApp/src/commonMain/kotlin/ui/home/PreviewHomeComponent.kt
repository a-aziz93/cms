package ui.home

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewHomeComponent: HomeComponent, ComponentContext by PreviewComponentContext {

}