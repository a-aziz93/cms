package digital.sadad.project.core.config.model.plugin.cachingheaders

import io.ktor.http.*

data class CacheContentTypeOptionConfig(
    val contentType: ContentType,
    val cacheControl: CacheControlConfig,
)