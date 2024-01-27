package digital.sadad.project.core.service.security.session

import digital.sadad.project.core.service.security.ChallengeableAuthService
import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
import digital.sadad.project.core.service.security.UserIdPrincipalMetadata
import digital.sadad.project.core.service.security.session.model.UserSession
import io.ktor.server.auth.*

class SessionAuthService(

) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    suspend fun validate(session: UserSession): Principal? =
        if (principal.username.startsWith("jet")) {
            principal
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}