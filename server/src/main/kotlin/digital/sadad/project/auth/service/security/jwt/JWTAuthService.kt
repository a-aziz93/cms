package digital.sadad.project.auth.service.security.jwt

import digital.sadad.project.auth.model.security.jwt.JWT.Companion.USER_ROLES_CLAIM
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

interface JWTAuthService : SkipableAuthService, RBACAuthService {
    override fun roles(principal: Principal): Set<String> =
        (principal as JWTPrincipal).payload.claims?.get(USER_ROLES_CLAIM)?.asList(String::class.java)?.toSet()
            ?: emptySet()
}