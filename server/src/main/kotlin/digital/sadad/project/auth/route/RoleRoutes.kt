package digital.sadad.project.auth.route

import ROLE_ENDPOINT
import core.error.BadRequest
import core.error.Forbidden
import core.error.NotFound
import core.error.Unauthorized
import digital.sadad.project.auth.service.role.RoleService
import digital.sadad.project.core.error.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import mu.two.KotlinLogging
import org.koin.ktor.ext.inject

private val logger = KotlinLogging.logger {}

fun Application.roleRoutes() {

    // Dependency injection by Koin
    val roleService: RoleService by inject()

    routing {
        route("/$ROLE_ENDPOINT") {

        }
    }
}

// Handle errors
private suspend fun PipelineContext<Unit, ApplicationCall>.handleRoleError(
    error: Any
) {
    when (error) {
        // Roles
        is BadRequest -> call.respond(HttpStatusCode.BadRequest, error.message)
        is NotFound -> call.respond(HttpStatusCode.NotFound, error.message)
        is Unauthorized -> call.respond(HttpStatusCode.Unauthorized, error.message)
        is Forbidden -> call.respond(HttpStatusCode.Forbidden, error.message)
    }
}