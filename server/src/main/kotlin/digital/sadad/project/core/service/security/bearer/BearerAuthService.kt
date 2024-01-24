package digital.sadad.project.core.service.security.bearer

import digital.sadad.project.core.config.model.plugin.security.bearer.BearerAuthConfig
import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

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