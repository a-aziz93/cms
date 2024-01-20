package digital.sadad.project.core.config.model.cors

import io.ktor.http.*

data class CORSConfig(
    val hosts: MutableSet<CORSHostConfig>? = null,
    val headers: MutableSet<String>? = null,
    val methods: MutableSet<HttpMethod>? = null,
    val exposedHeaders: MutableSet<String>? = null,
    var allowCredentials: Boolean? = null,
    val maxAgeInSeconds: Long? = null,
    val allowSameOrigin: Boolean? = null,
    val allowNonSimpleContentTypes: Boolean? = null,
)