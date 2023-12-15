package ui.reset

import kotlinx.coroutines.flow.MutableStateFlow
import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewResetComponent : ResetComponent, ComponentContext by PreviewComponentContext {

    override val login = MutableStateFlow("login")

    override val inProgress = MutableStateFlow(false)

    override fun onLoginChanged(login: String) = Unit
    override fun onResetClick() = Unit
}