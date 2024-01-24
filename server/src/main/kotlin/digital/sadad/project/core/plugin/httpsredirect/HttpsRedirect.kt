package digital.sadad.project.core.plugin.hsts

import digital.sadad.project.core.config.model.plugin.httpsredirect.HTTPSRedirectConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.hsts.*
import io.ktor.server.plugins.httpsredirect.*

fun Application.configureHttpsRedirect(config: HTTPSRedirectConfig) {
    if (config.enable == true) {
        install(HttpsRedirect) {
            // The port to redirect to. By default 443, the default HTTPS port.
            sslPort = 443
            // 301 Moved Permanently, or 302 Found redirect.
            permanentRedirect = true
        }
    }
}