package digital.sadad.project.core.plugin.cachingheaders

import digital.sadad.project.core.config.model.plugin.defaultheaders.ConditionalHeadersConfig
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.conditionalheaders.*

fun Application.configureConditionalHeaders(config: ConditionalHeadersConfig) {
    if (config.enable == true) {
        install(ConditionalHeaders) {

        }
    }
}