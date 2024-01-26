package digital.sadad.project.core.plugin.dropwizardmetrics

import com.codahale.metrics.MetricAttribute
import com.codahale.metrics.Slf4jReporter
import com.codahale.metrics.jmx.JmxReporter
import digital.sadad.project.core.config.model.log.LogConfig
import digital.sadad.project.core.config.model.plugin.callloging.CallLoggingConfig
import digital.sadad.project.core.config.model.plugin.dropwizardmetrics.DropwizardMetricsConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level
import io.ktor.server.metrics.dropwizard.*
import java.util.concurrent.TimeUnit

fun Application.configureDropwizardMetrics(config: DropwizardMetricsConfig) {
    if (config.enable == true) {
        install(DropwizardMetrics) {
            config.baseName?.let { baseName = it }
            config.registerJvmMetricSets?.let { registerJvmMetricSets = it }

            config.slf4jReporter?.let {
                val slf4jReporter = Slf4jReporter.forRegistry(registry)

                it.logging?.let {
                    it.level?.let {
                        slf4jReporter.withLoggingLevel(Slf4jReporter.LoggingLevel.valueOf(it))
                    }
                }
                it.prefix?.let { slf4jReporter.prefixedWith(it) }
                it.rateUnit?.let { slf4jReporter.convertRatesTo(it) }
                it.durationUnit?.let { slf4jReporter.convertDurationsTo(it) }
                it.shutdownExecutorOnStop?.let { slf4jReporter.shutdownExecutorOnStop(it) }
                it.disabledMetricAttributes?.let { slf4jReporter.disabledMetricAttributes(it) }

                slf4jReporter
                    .build()
                    .start(it.start.initialDelay, it.start.period, it.start.unit)
            }

            config.jmxReporter?.let {
                val jmxReporter = JmxReporter.forRegistry(registry)

                it.rateUnit?.let { jmxReporter.convertRatesTo(it) }
                it.durationUnit?.let { jmxReporter.convertDurationsTo(it) }
                it.domain?.let { jmxReporter.inDomain(it) }
                it.specificDurationUnits?.let { jmxReporter.specificDurationUnits(it) }
                it.specificRateUnits?.let { jmxReporter.specificRateUnits(it) }

                jmxReporter
                    .build()
                    .start()
            }
        }
    }
}