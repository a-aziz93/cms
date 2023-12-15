package ui.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ui.home.HomeComponent
import ui.map.MapComponent
import ui.profile.ProfileComponent
import ui.reset.ResetComponent
import ui.settings.SettingsComponent
import ui.signin.SignInComponent
import ui.signup.SignUpComponent

interface MainComponent{
    
    val childStack: Value<ChildStack<*, Child>>
    
    fun onOutput(output:Output)

    sealed class Child {
        class SignUp(val component: SignUpComponent) : Child()
        class SignIn(val component: SignInComponent) : Child()
        class Reset(val component: ResetComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
        class Home(val component: HomeComponent) : Child()
        class Map(val component: MapComponent) : Child()
        class Settings(val component: SettingsComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object SignUp : Config()
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
        data object Settings : Config()
    }
    
    sealed class Output {
        data object NavigateBack : Output()
        data object NavigateToSignIn : Output()
        data object NavigateToProfile : Output()
        data object NavigateToHome : Output()
        data object NavigateToMap : Output()
        data object NavigateToSettings : Output()
    }
}