package digital.sadad.project.core.service.security

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import io.ktor.server.application.*
import io.ktor.server.sessions.*

interface SkipableAuthService {
    fun skip(call: ApplicationCall): Boolean = call.sessions.get<UserIdPrincipalMetadata>() != null
}