package ui.dynamicfeatures.dynamicfeature

interface FeatureInstaller {

    suspend fun install(name: String): Result

    sealed interface Result {
        data object Installed : Result
        data object Cancelled : Result
        data object Error : Result
    }
}