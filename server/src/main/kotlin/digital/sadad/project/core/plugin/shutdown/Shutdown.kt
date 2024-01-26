package digital.sadad.project.core.plugin.shutdown

import digital.sadad.project.core.config.model.plugin.shutdown.ShutDownConfig
import io.ktor.server.application.*
import io.ktor.server.engine.*


fun Application.configureShutdown(config: ShutDownConfig) {
    if (config.enable == true) {
        install(ShutDownUrl.ApplicationCallPlugin) {
            config.shutDownUrl?.let { shutDownUrl = it }
            config.exitCodeSupplier?.let { exitCodeSupplier = { it } }
        }
    }
}