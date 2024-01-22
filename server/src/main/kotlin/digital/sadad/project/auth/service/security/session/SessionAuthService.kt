package digital.sadad.project.auth.service.security.session

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.service.security.ChallengeableAuthService
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*

class SessionAuthService(

) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    suspend fun validate(principal: UserIdPrincipalMetadata): Principal? =
        if (principal.username.startsWith("jet")) {
            principal
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}