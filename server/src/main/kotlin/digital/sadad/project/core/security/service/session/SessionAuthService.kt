package digital.sadad.project.core.security.service.session

import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.service.ChallengeableAuthService
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import digital.sadad.project.core.service.security.session.model.UserSession
import io.ktor.server.auth.*

class SessionAuthService(

) : ChallengeableAuthService,
    SkipableAuthService, RBACAuthService {

    suspend fun validate(session: UserSession): Principal? =
        if (principal.username.startsWith("jet")) {
            principal
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}