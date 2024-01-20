package digital.sadad.project.core.config.model.ratelimit

import kotlin.time.Duration

data class RateLimitConfig(
    val limit: Int,
    val refillPeriod: Duration,
    val initialSize: Int = limit,
)
