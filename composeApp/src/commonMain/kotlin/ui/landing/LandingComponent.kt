package ui.landing

interface LandingComponent {
    val onOutput: (Output) -> Unit
    
    sealed class Output {
        data object NavigateToMain : Output()
    }
}