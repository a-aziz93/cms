package digital.sadad.project.auth.service.security.bearer

import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

@Single
class BearerAuthService(

) : SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: BearerTokenCredential): Principal? =
        if (credential.token == "abc123") {
            UserIdPrincipal("jetbrains")
        } else {
            null
        }

}