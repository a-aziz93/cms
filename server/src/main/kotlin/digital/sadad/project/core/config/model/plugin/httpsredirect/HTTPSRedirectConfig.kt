package digital.sadad.project.core.config.model.plugin.httpsredirect

import digital.sadad.project.core.config.model.plugin.PluginConfig

class HTTPSRedirectConfig(
    enable: Boolean? = null,
    val sslPort: Int? = null,
    val permanentRedirect: Boolean? = null,
    val excludePrefix: String? = null,
    val excludeSuffix: String? = null,
) : PluginConfig(enable)