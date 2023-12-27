package digital.sadad.project.core.plugins

import digital.sadad.project.core.plugins.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSession() {
    // We can configure compression here
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
}