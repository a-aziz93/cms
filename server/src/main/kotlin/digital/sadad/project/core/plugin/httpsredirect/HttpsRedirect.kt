package digital.sadad.project.core.plugin.httpsredirect

import digital.sadad.project.core.config.model.plugin.httpsredirect.HTTPSRedirectConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.httpsredirect.*

fun Application.configureHttpsRedirect(
    config: HTTPSRedirectConfig,
    port: Int?,
) {
    if (config.enable == true) {
        install(HttpsRedirect) {
            // The port to redirect to. By default, 443, the default HTTPS port.
            config.sslPort?.let { sslPort = it } ?: port?.let { sslPort = it }

            // 301 Moved Permanently, or 302 Found redirect.
            config.permanentRedirect?.let { permanentRedirect = it }

            config.excludePrefix?.let { excludePrefix(it) }

            config.excludeSuffix?.let { excludeSuffix(it) }
        }
    }
}