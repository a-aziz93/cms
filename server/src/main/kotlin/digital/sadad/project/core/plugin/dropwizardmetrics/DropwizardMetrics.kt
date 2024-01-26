package digital.sadad.project.core.plugin.dropwizardmetrics

import digital.sadad.project.core.config.model.plugin.callloging.CallLoggingConfig
import digital.sadad.project.core.config.model.plugin.dropwizardmetrics.DropwizardMetricsConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureDropwizardMetrics(config: DropwizardMetricsConfig) {
    if (config.enable == true) {
        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }
    }
}