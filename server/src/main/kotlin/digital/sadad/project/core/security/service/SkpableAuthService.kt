package digital.sadad.project.core.security.service

import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import io.ktor.server.application.*
import io.ktor.server.sessions.*

interface SkipableAuthService {
    fun skip(call: ApplicationCall): Boolean = call.sessions.get<UserIdPrincipalMetadata>() != null
}