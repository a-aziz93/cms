package digital.sadad.project.core.plugin.cachingheaders

import digital.sadad.project.core.config.model.plugin.cachingheaders.CachingHeadersConfig
import digital.sadad.project.core.plugin.cachingheaders.extension.cacheControl
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*

fun Application.configureCachingHeaders(config: CachingHeadersConfig) {
    if (config.enable == true) {
        install(CachingHeaders) {
            options { call, outgoingContent ->
                config.rootOption?.let { CachingOptions(it.cacheControl()) }
                val contentType = outgoingContent.contentType?.withoutParameters()
                config.options?.find { it.contentType == contentType }
                    ?.let { CachingOptions(it.cacheControl.cacheControl()) }
            }
        }
    }
}