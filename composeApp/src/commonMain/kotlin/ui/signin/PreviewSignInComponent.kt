package ui.signin

import kotlinx.coroutines.flow.MutableStateFlow
import com.arkivanov.decompose.ComponentContext
import ui.PreviewComponentContext
import ui.main.MainComponent

class PreviewSignInComponent : SignInComponent, ComponentContext by PreviewComponentContext {

    override val login = MutableStateFlow("login")
    override val password = MutableStateFlow("password")
    override val inProgress = MutableStateFlow(false)

    override fun onLoginChanged(login: String) = Unit
    override fun onPasswordChanged(password: String) = Unit
    override fun onSignInClick() = Unit

    override fun onNavigate(config: MainComponent.Config) {
        
    }
}