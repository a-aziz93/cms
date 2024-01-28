package digital.sadad.project.core.security.service

import io.ktor.server.auth.*

interface RBACAuthService {
    fun roles(principal: Principal): Set<String> = emptySet()
}