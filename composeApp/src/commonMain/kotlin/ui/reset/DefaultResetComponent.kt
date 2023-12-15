package ui.reset

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import core.util.componentCoroutineScope
import data.registration.RegistrationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DefaultResetComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : ComponentContext by componentContext, ResetComponent {

    override val login = MutableStateFlow("")

    override val inProgress = MutableStateFlow(false)

    private val coroutineScope = componentCoroutineScope()

    override fun onLoginChanged(login: String) {
        this.login.value = login
    }

    override fun onResetClick() {
        coroutineScope.launch {
            inProgress.value = true
            // TODO reset function
            inProgress.value = false

        // TODO: navigate to the next screen
        }
    }
}