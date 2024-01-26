package digital.sadad.project.core.config.model.plugin.micrometermetrics

import kotlin.time.Duration


data class DistributionStatisticsConfig(
    val percentileHistogram: Boolean? = null,
    val percentiles: List<Double>? = null,
    val percentilePrecision: Int? = null,
    val serviceLevelObjectives: List<Double>? = null,
    val minimumExpectedValue: Double? = null,
    val maximumExpectedValue: Double? = null,
    val expiry: Duration? = null,
    val bufferLength: Int? = null,
)