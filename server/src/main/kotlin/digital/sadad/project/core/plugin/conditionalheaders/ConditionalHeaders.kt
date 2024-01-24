package digital.sadad.project.core.plugin.cachingheaders

import digital.sadad.project.core.config.model.plugin.defaultheaders.ConditionalHeadersConfig
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.conditionalheaders.*
import java.io.File
import java.util.*

fun Application.configureConditionalHeaders(config: ConditionalHeadersConfig) {
    if (config.enable == true) {
        install(ConditionalHeaders) {
            config.versionHeadersPath?.let {
                val file = File(it)
                version { call, outgoingContent ->
                    when (outgoingContent.contentType?.withoutParameters()) {
                        ContentType.Text.CSS -> listOf(
                            EntityTagVersion(file.lastModified().hashCode().toString()),
                            LastModifiedVersion(Date(file.lastModified()))
                        )

                        else -> emptyList()
                    }
                }
            }
        }
    }
}