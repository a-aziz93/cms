package digital.sadad.project.core.config.model.plugin.dropwizardmetrics

import java.util.concurrent.TimeUnit

data class ScheduledReporterStartConfig(
    val period: Long,
    val initialDelay: Long = period,
    val unit: TimeUnit,
)
