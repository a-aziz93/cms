package digital.sadad.project.core.plugin.defaultheaders

import digital.sadad.project.core.config.model.plugin.defaultheaders.DefaultHeadersConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureDefaultHeaders(config: DefaultHeadersConfig) {
    if (config.enable == true) {
        install(DefaultHeaders) {
            config.headers?.forEach {
                header(it.key, it.value)
            }
        }
    }
}