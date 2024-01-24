package digital.sadad.project.core.service.security

import io.ktor.server.auth.*

interface RBACAuthService {
    fun roles(principal: Principal): Set<String> = emptySet()
}