package digital.sadad.project.core.security.session.service

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.service.ChallengeableAuthService
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import digital.sadad.project.core.security.session.model.UserSession
import io.ktor.server.auth.*

class SessionAuthService(
    val config: SessionAuthConfig,
) : ChallengeableAuthService,
    SkipableAuthService, RBACAuthService {

    suspend fun validate(session: UserSession): Principal? = session.principal

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}