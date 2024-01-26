package digital.sadad.project.core.config.model.plugin.dropwizardmetrics

import com.codahale.metrics.MetricRegistry
import digital.sadad.project.core.config.model.plugin.PluginConfig
import io.ktor.server.metrics.dropwizard.*

class DropwizardMetricsConfig(
    enable: Boolean? = null,
    val baseName: String? = null,
    val registerJvmMetricSets: Boolean? = null,
    val slf4jReporter: Slf4jReporterConfig? = null,
    val jmxReporter: JmxReporterConfig? = null,
) : PluginConfig(enable)