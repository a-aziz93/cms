package digital.sadad.project.core.plugin.ratelimit

import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import org.koin.ktor.ext.inject

fun Application.configureRateLimit() {

    val appConfig: AppConfig by inject()
    appConfig.config.rateLimits?.let {
        install(RateLimit) {
            it.global?.let {
                global {
                    rateLimiter(it.limit, it.refillPeriod, it.initialSize)
                }
            }
            it.specific?.forEach {
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