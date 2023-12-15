package ui.reset

import kotlinx.coroutines.flow.StateFlow

interface ResetComponent {
    
    val login: StateFlow<String>

    val inProgress: StateFlow<Boolean>

    fun onLoginChanged(login: String)

    fun onResetClick()
}