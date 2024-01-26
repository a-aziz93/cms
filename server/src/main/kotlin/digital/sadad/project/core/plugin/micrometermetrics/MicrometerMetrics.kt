package digital.sadad.project.core.plugin.micrometermetrics

import digital.sadad.project.core.config.model.plugin.micrometermetrics.MicrometerMetricsConfig
import digital.sadad.project.core.config.model.plugin.micrometermetrics.MicrometerMetricsType
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics
import io.micrometer.core.instrument.binder.system.ProcessorMetrics
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import java.time.Duration
import kotlin.time.DurationUnit
import digital.sadad.project.core.config.model.plugin.micrometermetrics.MicrometerMetricsType.*

fun Application.configureMicrometerMetrics(config: MicrometerMetricsConfig) {
    if (config.enable == true) {
        val appMicrometerRegistry =
            when (config.type) {
                PROMETHEUS -> PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
            }
        install(MicrometerMetrics) {
            registry = appMicrometerRegistry

            config.metricName?.let { metricName = it }

            config.distinctNotRegisteredRoutes?.let { distinctNotRegisteredRoutes = it }

            meterBinders = listOfNotNull(
                if (config.classLoaderMetrics == true) ClassLoaderMetrics() else null,
                if (config.jvmMemoryMetrics == true) JvmMemoryMetrics() else null,
                if (config.jvmGcMetrics == true) JvmGcMetrics() else null,
                if (config.processorMetrics == true) ProcessorMetrics() else null,
                if (config.jvmThreadMetrics == true) JvmThreadMetrics() else null,
                if (config.fileDescriptorMetrics == true) FileDescriptorMetrics() else null,
            )

            config.distributionStatistics?.let {

                val distributionStatisticConfigBuilder = DistributionStatisticConfig.Builder()

                it.percentileHistogram?.let {
                    if (it) {
                        distributionStatisticConfigBuilder.percentilesHistogram(it)
                    }
                }
                it.percentiles?.let { distributionStatisticConfigBuilder.percentiles(*it.toDoubleArray()) }
                it.percentilePrecision?.let { distributionStatisticConfigBuilder.percentilePrecision(it) }
                it.serviceLevelObjectives?.let { distributionStatisticConfigBuilder.serviceLevelObjectives(*it.toDoubleArray()) }
                it.minimumExpectedValue?.let { distributionStatisticConfigBuilder.minimumExpectedValue(it) }
                it.maximumExpectedValue?.let { distributionStatisticConfigBuilder.maximumExpectedValue(it) }
                it.expiry?.let { distributionStatisticConfigBuilder.expiry(Duration.ofNanos(it.toLong(DurationUnit.NANOSECONDS))) }
                it.bufferLength?.let { distributionStatisticConfigBuilder.bufferLength(it) }

                distributionStatisticConfig = distributionStatisticConfigBuilder.build()
            }
        }
        routing {
            get("/metrics") {
                call.respond(appMicrometerRegistry.scrape())
            }
        }
    }
}