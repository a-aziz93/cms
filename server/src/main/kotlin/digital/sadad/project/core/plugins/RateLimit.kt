package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.ratelimit.*
import org.koin.ktor.ext.inject
import kotlin.time.Duration.Companion.seconds

fun Application.configureRateLimit() {

    val appConfig: AppConfig by inject()
    val rateLimitersConfig = appConfig.config.rateLimiters

    if (rateLimitersConfig != null) {
        install(RateLimit) {
            rateLimitersConfig.forEach {
                if (it.value.isGlobal == true) {
                    global {
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