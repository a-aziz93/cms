package ui.dynamicfeatures.dynamicfeature

import ui.dynamicfeatures.dynamicfeature.FeatureInstaller.Result

object DefaultFeatureInstaller : FeatureInstaller {

    override suspend fun install(name: String): Result =Result.Installed
}