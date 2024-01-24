package digital.sadad.project.core.plugin.hsts

import digital.sadad.project.core.config.model.plugin.hsts.HSTSConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.hsts.*

fun Application.configureHSTS(config: HSTSConfig) {
    if (config.enable == true) {
        install(HSTS) {
            config.global?.let {
                it.preload?.let { }
                it.includeSubDomains?.let { includeSubDomains = it }
                it.maxAgeInSeconds?.let { maxAgeInSeconds = it }
                it.customDirectives?.let { customDirectives + it }
            }

            config.hostSpecific?.forEach {
                withHost(it.key) {
                    it.value.preload?.let { }
                    it.value.includeSubDomains?.let { includeSubDomains = it }
                    it.value.maxAgeInSeconds?.let { maxAgeInSeconds = it }
                    it.value.customDirectives?.let { customDirectives + it }
                }
            }
        }
    }
}