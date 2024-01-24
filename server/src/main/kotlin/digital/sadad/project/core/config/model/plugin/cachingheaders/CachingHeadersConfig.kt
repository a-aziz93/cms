package digital.sadad.project.core.config.model.plugin.cachingheaders

import digital.sadad.project.core.config.model.plugin.PluginConfig
import io.ktor.http.*

class CachingHeadersConfig(
    enable: Boolean? = null,
    val rootOption: CacheControlConfig? = null,
    val options: Set<CacheContentTypeOptionConfig>? = null,
) : PluginConfig(enable)