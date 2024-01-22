package digital.sadad.project.auth.service.security.session

import digital.sadad.project.auth.model.security.UserPrincipal
import digital.sadad.project.auth.service.security.ChallengeableAuthService
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

@Single
class SessionAuthService(

) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    fun validate(principal: UserPrincipal): Principal? =
        if (principal.username.startsWith("jet")) {
            principal
        } else {
            null
        }

}