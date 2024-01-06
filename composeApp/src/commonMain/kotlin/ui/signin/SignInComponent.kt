package ui.signin

import kotlinx.coroutines.flow.StateFlow
import ui.common.component.navigation.NavigationComponent
import ui.main.MainComponent

interface SignInComponent: NavigationComponent<MainComponent.Config> {
    
    val login: StateFlow<String>

    val password: StateFlow<String>

    val inProgress: StateFlow<Boolean>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onSignInClick()
}