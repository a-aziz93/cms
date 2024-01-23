package digital.sadad.project.core.config.model.plugin.cors

import digital.sadad.project.core.config.model.plugin.PluginConfig
import io.ktor.http.*

class CORSConfig(
    enable: Boolean? = null,
    val hosts: MutableSet<CORSHostConfig>? = null,
    val headers: MutableSet<String>? = null,
    val methods: MutableSet<HttpMethod>? = null,
    val exposedHeaders: MutableSet<String>? = null,
    var allowCredentials: Boolean? = null,
    val maxAgeInSeconds: Long? = null,
    val allowSameOrigin: Boolean? = null,
    val allowNonSimpleContentTypes: Boolean? = null,
) : PluginConfig(enable)