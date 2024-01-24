package digital.sadad.project.core.plugin.hsts

import digital.sadad.project.core.config.model.plugin.hsts.HSTSConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.hsts.*

fun Application.configureHSTS(config: HSTSConfig) {
    if (config.enable == true) {
        install(HSTS) {
            includeSubDomains = true
        }
    }
}