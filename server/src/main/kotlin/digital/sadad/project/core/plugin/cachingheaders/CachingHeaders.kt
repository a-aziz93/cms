package digital.sadad.project.core.plugin.cachingheaders

import digital.sadad.project.core.config.model.plugin.cachingheaders.CachingHeadersConfig
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*

fun Application.configureCachingHeaders(config: CachingHeadersConfig) {
    if (config.enable == true) {
        install(CachingHeaders) {
            options { call, outgoingContent ->
                when (outgoingContent.contentType?.withoutParameters()) {
                    ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
                    else -> null
                }
            }
        }
    }
}