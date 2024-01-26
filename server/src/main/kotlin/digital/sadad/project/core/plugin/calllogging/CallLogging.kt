package digital.sadad.project.core.plugin.calllogging

import digital.sadad.project.core.config.model.plugin.callloging.CallLoggingConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureCallLogging(config: CallLoggingConfig) {
    if (config.enable == true) {
        install(CallLogging) {
            config.logging?.let {
                it.level?.let { level = Level.valueOf(it) }
            }
            config.disableDefaultColors?.let {
                if (it) {
                    disableDefaultColors()
                }
            }
            config.disableForStaticContent?.let {
                if (it) {
                    disableForStaticContent()
                }
            }
        }
    }
}