package digital.sadad.project.core.security.bearer.service

import digital.sadad.project.core.config.model.plugin.security.bearer.BearerAuthConfig
import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import io.ktor.server.auth.*

class BearerAuthService(
    val config: BearerAuthConfig,
) : SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: BearerTokenCredential): Principal? =
        if (credential.token == "abc123") {
            UserIdPrincipal("jetbrains")
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}