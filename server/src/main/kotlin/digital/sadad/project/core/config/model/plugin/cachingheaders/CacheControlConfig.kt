package digital.sadad.project.core.config.model.plugin.cachingheaders

import io.ktor.http.*

data class CacheControlConfig(
    val type: CacheControlType,
    val maxAgeSeconds: Int,
    val proxyMaxAgeSeconds: Int? = null,
    val mustRevalidate: Boolean = false,
    val proxyRevalidate: Boolean = false,
    val visibility: CacheControl.Visibility? = null,
)