package ui.profile

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewProfileComponent: ProfileComponent, ComponentContext by PreviewComponentContext {

}