package digital.sadad.project.core.security.service.session.model

import io.ktor.server.auth.*

interface UserSession : Principal {
    val authName: String?
    val roles: Set<String>?
    val count: Long
}