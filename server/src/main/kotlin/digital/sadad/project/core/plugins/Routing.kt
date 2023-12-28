package digital.sadad.project.core.plugins

import digital.sadad.project.core.error.HttpError
import digital.sadad.project.auth.route.roleRoutes
import digital.sadad.project.auth.route.userRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

/**
 * Define the routing of our application based a DSL
 * https://ktor.io/docs/routing-in-ktor.html
 * we can define our routes in separate files like routes package
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("\uD83D\uDC4B Hello Reactive API REST!")
        }
    }

    // Add our routes
    roleRoutes() // Role routes
    userRoutes() // User routes
}

suspend fun PipelineContext<Unit, ApplicationCall>.handleHttpError(
    error: HttpError
)= call.respond(HttpStatusCode.fromValue(error.statusCode), error.message)
