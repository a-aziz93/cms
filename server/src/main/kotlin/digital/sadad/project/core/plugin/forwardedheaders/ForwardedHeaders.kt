package digital.sadad.project.core.plugin.forwardedheaders

import digital.sadad.project.core.config.model.plugin.forwardedheaders.ForwardedHeadersConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.forwardedheaders.*

fun Application.configureForwardedHeaders(config: ForwardedHeadersConfig) {
    if (config.enable == true) {
        install(ForwardedHeaders) // WARNING: for security, do not include this if not behind a reverse proxy
        install(XForwardedHeaders) // WARNING: for security, do not include this if not behind a reverse proxy {

    }
}