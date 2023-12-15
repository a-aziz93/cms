package ui.dynamicfeatures.dynamicfeature

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import ui.dynamicfeatures.dynamicfeature.FeatureInstaller.Result

class DefaultFeatureInstaller(
    context: Context
) : FeatureInstaller {

    private val manager = SplitInstallManagerFactory.create(context)
    private val installedFeatures = HashSet<String>()

    override suspend fun install(name: String): Result=checkFeatureInstalled(name)?.installFeature(name)

    private fun checkFeatureInstalled(name: String):Result.Installed? =
        if (manager.installedModules.contains(name)) null else Result.Installed

    private suspend fun installFeature(name: String): Result = flow {
        val listener =
            StateUpdatedListener(
                onInstalled = { runBlocking { emit(Result.Installed) } },
                onCancelled = { runBlocking { emit(Result.Cancelled) } }
            )

        manager.registerListener(listener)

        manager
            .startInstall(
                SplitInstallRequest
                    .newBuilder()
                    .addModule(name)
                    .build()
            )
            .addOnSuccessListener { listener.sessionId = it }
            .addOnFailureListener { runBlocking {emit(Result.Error)} }
    }.single()

    private class StateUpdatedListener(
        private val onInstalled: () -> Unit,
        private val onCancelled: () -> Unit,
        ) : SplitInstallStateUpdatedListener {
            var sessionId: Int = 0

        override fun onStateUpdate(state: SplitInstallSessionState) {
            if (state.sessionId() != sessionId) {
                return
            }

            when (state.status()) {
                SplitInstallSessionStatus.INSTALLED -> {
                    sessionId = 0
                    onInstalled()
                }

                SplitInstallSessionStatus.CANCELED -> {
                    sessionId = 0
                    onCancelled()
                }

                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> Unit // Handle user confirmation
                else -> Unit
            }
        }
        }
}