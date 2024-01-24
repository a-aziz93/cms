package digital.sadad.project.core.plugin.xhttpmethodoverride

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.xhttpmethodoverride.XHttpMethodOverrideConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.methodoverride.*
import org.koin.ktor.ext.inject

fun Application.configureXHttpMethodOverride(config: XHttpMethodOverrideConfig) {
    if (config.enable == true) {
        install(XHttpMethodOverride) {
            config.headerName?.let { headerName = it }
        }
    }
}