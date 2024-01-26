package digital.sadad.project.core.config.model.plugin.dropwizardmetrics

import com.codahale.metrics.MetricAttribute
import com.codahale.metrics.MetricFilter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.Slf4jReporter
import digital.sadad.project.core.config.model.log.LogConfig
import org.slf4j.Logger
import org.slf4j.Marker
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

data class Slf4jReporterConfig(
    val logging: LogConfig? = null,
    val prefix: String? = null,
    val rateUnit: TimeUnit? = null,
    val durationUnit: TimeUnit? = null,
    val shutdownExecutorOnStop: Boolean? = null,
    val disabledMetricAttributes: Set<MetricAttribute>? = null,
    val start: ScheduledReporterStartConfig,
)
