package digital.sadad.project.core.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.methodoverride.*

fun Application.configureXHttpMethodOverride() {
    install(XHttpMethodOverride){

    }
}