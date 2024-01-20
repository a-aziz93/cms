package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.methodoverride.*
import org.koin.ktor.ext.inject

fun Application.configureXHttpMethodOverride() {
    val appConfig: AppConfig by inject()
    appConfig.config.xHttpMethodOverride?.let {
        install(XHttpMethodOverride) {
            it.headerName?.let { headerName = it }
        }
    }
}