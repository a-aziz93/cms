package digital.sadad.project.auth.service.security.bearer

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.security.bearer.BearerAuthConfig
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