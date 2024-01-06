package ui.selfsignup

import kotlinx.coroutines.flow.StateFlow
import ui.component.navigation.NavigationComponent
import ui.main.MainComponent

interface SelfSignUpComponent {

    val login: StateFlow<String>

    val password: StateFlow<String>

    val inProgress: StateFlow<Boolean>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onSignUpClick()
}