package ui.signin

import kotlinx.coroutines.flow.StateFlow

interface SignInComponent {
    val onOutput: (Output) -> Unit
    
    val login: StateFlow<String>

    val password: StateFlow<String>

    val inProgress: StateFlow<Boolean>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onSignInClick()
    
    sealed class Output {
        data object NavigateSignUp : Output()
        data object NavigateToReset: Output()
    }
}