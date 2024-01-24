package digital.sadad.project.core.restful

import ROLE_ENDPOINT
import digital.sadad.project.auth.service.role.RoleService
import io.ktor.server.application.*
import io.ktor.server.routing.*
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