package ui.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ui.adminsignup.AdminSignUpComponent
import ui.cdex.CDexComponent
import ui.cdox.CDoxComponent
import ui.cms.CMSComponent
import ui.component.navigation.EventComponent
import ui.component.navigation.NavigationChild
import ui.component.navigation.NavigationComponent
import ui.component.navigation.NavigationConfig
import ui.dashboard.DashboardComponent
import ui.home.HomeComponent
import ui.map.MapComponent
import ui.profile.DefaultProfileComponent
import ui.queue.QueueComponent
import ui.reset.ResetComponent
import ui.settings.SettingsComponent
import ui.signin.SignInComponent
import ui.selfsignup.SelfSignUpComponent
import kotlin.reflect.KClass

interface MainComponent : NavigationComponent<MainComponent.Config>, EventComponent<MainComponent.Event> {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child : NavigationChild() {
        class SelfSignUp(val component: SelfSignUpComponent) : Child()
        class AdminSignUp(val component: AdminSignUpComponent) : Child()
        class SignIn(val component: SignInComponent) : Child()
        class Reset(val component: ResetComponent) : Child()
        class Profile(val component: DefaultProfileComponent) : Child()
        class Home(val component: HomeComponent) : Child()
        class Map(val component: MapComponent) : Child()
        class CMS(val component: CMSComponent) : Child()
        class Queue(val component: QueueComponent) : Child()
        class CDox(val component: CDoxComponent) : Child()
        class CDex(val component: CDexComponent) : Child()
        class Dashboard(val component: DashboardComponent) : Child()
        class Settings(val component: SettingsComponent) : Child()

        fun indexOfChild() =
            this::class.nestedClasses.indexOf(this::class)
    }

    @Serializable
    sealed class Config : NavigationConfig() {
        @Serializable
        data object SelfSignUp : Config()

        @Serializable
        data object AdminSignUp : Config()

        @Serializable
        data object SignIn : Config()

        @Serializable
        data object Reset : Config()

        @Serializable
        data object Profile : Config()

        @Serializable
        data object Home : Config()

        @Serializable
        data object Map : Config()

        @Serializable
        data object Dashboard : Config()

        @Serializable
        data object CMS : Config()

        @Serializable
        data object Queue : Config()

        @Serializable
        data object CDox : Config()

        @Serializable
        data object CDex : Config()

        @Serializable
        data object Settings : Config()
    }

    enum class Event {
        NAVIGATE_BACK,
    }
}