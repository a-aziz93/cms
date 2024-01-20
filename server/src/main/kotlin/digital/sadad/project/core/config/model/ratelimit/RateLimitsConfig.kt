package digital.sadad.project.core.config.model.ratelimit

data class RateLimitsConfig(
    val global: RateLimitConfig? = null,
    val specific: Map<String, RateLimitConfig>? = null,
)
