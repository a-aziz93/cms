package digital.sadad.project.auth.service.security

import digital.sadad.project.auth.model.security.UserIDPrincipal
import io.ktor.server.application.*
import io.ktor.server.sessions.*

interface SkipableAuthService {
    fun skip(call: ApplicationCall): Boolean = call.sessions.get<UserIDPrincipal>() != null
}