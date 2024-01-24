package digital.sadad.project.core.plugin.partialcontent

import digital.sadad.project.core.config.model.plugin.partialcontent.PartialContentConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.partialcontent.*

fun Application.configurePartialContent(config: PartialContentConfig) {
    if (config.enable == true) {
        install(PartialContent) {
            // Maximum number of ranges that will be accepted from an HTTP request.
            // If the HTTP request specifies more ranges, they will all be merged into a single range.
            config.maxRangeCount?.let { maxRangeCount = it }
        }
    }
}