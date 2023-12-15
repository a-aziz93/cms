package ui.settings

import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext
import ui.settings.SettingsComponent

class PreviewSettingsComponent: SettingsComponent, ComponentContext by PreviewComponentContext {

}