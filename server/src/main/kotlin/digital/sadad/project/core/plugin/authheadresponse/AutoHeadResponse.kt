package digital.sadad.project.core.plugin.authheadresponse

import digital.sadad.project.core.config.model.plugin.autoheadresponse.AutoHeadResponseConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*

fun Application.configureAutoHeadResponse(config: AutoHeadResponseConfig) {
    if (config.enable == true) {
        install(AutoHeadResponse) {

        }
    }
}