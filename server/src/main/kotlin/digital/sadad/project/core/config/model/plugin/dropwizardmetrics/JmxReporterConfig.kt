package digital.sadad.project.core.config.model.plugin.dropwizardmetrics

import com.codahale.metrics.MetricFilter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.jmx.ObjectNameFactory
import java.util.concurrent.TimeUnit
import javax.management.MBeanServer

data class JmxReporterConfig(
    val rateUnit: TimeUnit? = null,
    val durationUnit: TimeUnit? = null,
    val domain: String? = null,
    val specificDurationUnits: Map<String, TimeUnit>? = null,
    val specificRateUnits: Map<String, TimeUnit>? = null,
)
