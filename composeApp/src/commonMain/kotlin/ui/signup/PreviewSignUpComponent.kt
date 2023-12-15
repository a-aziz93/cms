package ui.signup

import kotlinx.coroutines.flow.MutableStateFlow
import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext

class PreviewSignUpComponent : SignUpComponent, ComponentContext by PreviewComponentContext {

    override val login = MutableStateFlow("login")
    override val password = MutableStateFlow("password")
    override val inProgress = MutableStateFlow(false)

    override fun onLoginChanged(login: String) = Unit
    override fun onPasswordChanged(password: String) = Unit
    override fun onSignInClick() = Unit
}