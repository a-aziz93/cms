package digital.sadad.project.core.plugin.ratelimit

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.ratelimit.RateLimitsConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import org.koin.ktor.ext.inject

fun Application.configureRateLimit(config: RateLimitsConfig) {
    if (config.enable == true) {
        install(RateLimit) {
            config.global?.let {
                global {
                    rateLimiter(it.limit, it.refillPeriod, it.initialSize)
                }
            }
            config.specific?.forEach {
                if (it.key.isBlank()) {
                    register {
                        rateLimiter(it.value.limit, it.value.refillPeriod, it.value.initialSize)
                    }
                } else {
                    register(RateLimitName(it.key)) {
                        rateLimiter(it.value.limit, it.value.refillPeriod, it.value.initialSize)
                    }
                }
            }
        }
    }
}