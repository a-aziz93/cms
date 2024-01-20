package digital.sadad.project.core.plugins

import digital.sadad.project.auth.network.restful.roleRoutes
import digital.sadad.project.auth.network.restful.userRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureResources() {
    install(Resources){

    }
}