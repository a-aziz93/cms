package digital.sadad.project.core.plugins

import core.error.HttpError
import digital.sadad.project.auth.network.restful.roleRoutes
import digital.sadad.project.auth.network.restful.userRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import java.io.File

/**
 * Define the routing of our application based a DSL
 * https://ktor.io/docs/routing-in-ktor.html
 * we can define our routes in separate files like routes package
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Reactive API REST!")
        }
        staticRootFolder = File("static")
    }
    // Add our routes
    roleRoutes() // Role routes
    userRoutes() // User routes
}

suspend fun PipelineContext<Unit, ApplicationCall>.handleHttpError(
    error: HttpError
) = call.respond(HttpStatusCode.fromValue(error.statusCode), error.message)
