package ui.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import core.util.componentCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ui.main.MainComponent

class DefaultSignInComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    val onNavigateHandler: (MainComponent.Config) -> Unit
) : ComponentContext by componentContext, SignInComponent {

    override val login = MutableStateFlow("")

    override val password = MutableStateFlow("")

    override val inProgress = MutableStateFlow(false)

    private val coroutineScope = componentCoroutineScope()

    override fun onLoginChanged(login: String) {
        this.login.value = login
    }

    override fun onPasswordChanged(password: String) {
        this.password.value = password
    }

    override fun onSignInClick() {
        coroutineScope.launch {
            inProgress.value = true
//            authorizationRepository.signIn(login.value, password.value)
            inProgress.value = false

            // TODO: navigate to the next screen
        }
    }

    override fun onNavigate(config: MainComponent.Config) = onNavigateHandler(config)
}