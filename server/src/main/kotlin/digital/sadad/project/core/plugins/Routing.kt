package digital.sadad.project.core.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import digital.sadad.project.auth.route.usersRoutes

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
    usersRoutes() // Users routes
}
