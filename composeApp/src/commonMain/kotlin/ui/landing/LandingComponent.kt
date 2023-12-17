package ui.landing

interface LandingComponent {
    val onOutput: (Output) -> Unit
    
    sealed class Output {
        data object NavigateToMain : Output()
//        data object NavigateToSignIn : Output()
//        data object NavigateToSignUp : Output()
    }
}