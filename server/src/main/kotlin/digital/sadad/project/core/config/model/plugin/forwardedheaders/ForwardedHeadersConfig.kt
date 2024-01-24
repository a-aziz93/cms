package digital.sadad.project.core.config.model.plugin.forwardedheaders

import digital.sadad.project.core.config.model.plugin.PluginConfig
import io.ktor.http.*

class ForwardedHeadersConfig(
    enable: Boolean? = null,
    val useFirst: Boolean? = null,
    val useLast: Boolean? = null,
    val skipLastProxies: Int? = null,
    val skipKnownProxies: List<String>? = null,
    val xForwardedHostHeaders: List<String>? = null,
    val xForwardedProtoHeaders: List<String>? = null,
    val xForwardedForHeaders: List<String>? = null,
    val xForwardedHttpsFlagHeaders: List<String>? = null,
    val xForwardedPortHeaders: List<String>? = null,
) : PluginConfig(enable)