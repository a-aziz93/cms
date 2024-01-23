package digital.sadad.project.core.plugin.authheadresponse

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*

fun Application.configureAutoHeadResponse() {
    install(AutoHeadResponse){

    }
}