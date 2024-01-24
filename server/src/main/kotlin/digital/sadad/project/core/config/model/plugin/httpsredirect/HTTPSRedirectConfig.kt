package digital.sadad.project.core.config.model.plugin.httpsredirect

import digital.sadad.project.core.config.model.plugin.PluginConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*

class HTTPSRedirectConfig(
    enable: Boolean? = null,
    val sslPort: Int? = null,
    val permanentRedirect: Boolean? = null,
    val excludePrefix: String? = null,
    val excludeSuffix: String? = null,
) : PluginConfig(enable)