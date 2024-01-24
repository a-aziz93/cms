package digital.sadad.project.core.plugin.cachingheaders.extension

import digital.sadad.project.core.config.model.plugin.cachingheaders.CacheControlConfig
import digital.sadad.project.core.config.model.plugin.cachingheaders.CacheControlType
import io.ktor.http.*

fun CacheControlConfig.cacheControl() = when (this.type) {
    CacheControlType.NO_CACHE -> CacheControl.NoCache(this.visibility)
    CacheControlType.NO_STORE -> CacheControl.NoStore(this.visibility)
    CacheControlType.MAX_AGE -> CacheControl.MaxAge(
        this.maxAgeSeconds,
        this.proxyMaxAgeSeconds,
        this.mustRevalidate,
        this.proxyRevalidate,
        this.visibility
    )
}