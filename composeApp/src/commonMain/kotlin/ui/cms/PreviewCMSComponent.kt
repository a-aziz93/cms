package ui.cms

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewCMSComponent: CMSComponent, ComponentContext by PreviewComponentContext {

}