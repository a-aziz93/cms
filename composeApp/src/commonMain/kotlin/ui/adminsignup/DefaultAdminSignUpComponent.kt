package ui.adminsignup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import core.util.componentCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DefaultAdminSignUpComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : ComponentContext by componentContext, AdminSignUpComponent {


}