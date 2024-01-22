package digital.sadad.project.auth.service.security

import io.ktor.server.auth.*

interface RBACAuthService {
    fun roles(principal: Principal): Set<String> = emptySet()
}