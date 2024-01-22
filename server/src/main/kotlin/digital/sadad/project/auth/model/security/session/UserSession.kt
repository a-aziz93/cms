package digital.sadad.project.auth.model.security.session

import io.ktor.server.auth.*

interface UserSession : Principal {
    val roles: Set<String>?
    val count: Long
}