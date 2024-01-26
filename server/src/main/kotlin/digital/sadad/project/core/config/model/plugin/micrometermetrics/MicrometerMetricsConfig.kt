package digital.sadad.project.core.config.model.plugin.micrometermetrics

import digital.sadad.project.core.config.model.plugin.PluginConfig

class MicrometerMetricsConfig(
    enable: Boolean? = null,
    val type: MicrometerMetricsType,
    val metricName: String? = null,
    val distinctNotRegisteredRoutes: Boolean? = null,
    val classLoaderMetrics: Boolean? = null,
    val jvmMemoryMetrics: Boolean? = null,
    val jvmGcMetrics: Boolean? = null,
    val processorMetrics: Boolean? = null,
    val jvmThreadMetrics: Boolean? = null,
    val fileDescriptorMetrics: Boolean? = null,
    val distributionStatistics: DistributionStatisticsConfig? = null
) : PluginConfig(enable)