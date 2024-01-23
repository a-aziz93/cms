package digital.sadad.project.core.config.model.plugin.ratelimit

import digital.sadad.project.core.config.model.plugin.PluginConfig

class RateLimitsConfig(
    enable: Boolean? = null,
    val global: RateLimitConfig? = null,
    val specific: Map<String, RateLimitConfig>? = null,
) : PluginConfig(enable)
