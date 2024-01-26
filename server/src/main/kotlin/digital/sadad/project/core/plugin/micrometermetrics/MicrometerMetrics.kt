package digital.sadad.project.core.plugin.micrometermetrics

import digital.sadad.project.core.config.model.plugin.applicationmonitoring.ApplicationMonitoringConfig
import digital.sadad.project.core.config.model.plugin.micrometermetrics.MicrometerMetricsConfig
import digital.sadad.project.core.plugin.applicationmonitoring.ApplicationMonitoringPlugin
import io.ktor.server.application.*

fun Application.configureMicrometerMetrics(config: MicrometerMetricsConfig) {
    if (config.enable == true) {
        install(ApplicationMonitoringPlugin) {

        }
    }
}