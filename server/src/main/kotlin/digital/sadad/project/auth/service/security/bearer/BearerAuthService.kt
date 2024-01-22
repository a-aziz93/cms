package digital.sadad.project.auth.service.security.bearer

import digital.sadad.project.auth.model.security.jwt.JWTHS256
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single

@Single
class BearerAuthService(

) : SkipableAuthService, RBACAuthService {

    fun validate(credential: BearerTokenCredential): Principal? =
        if (credential.token == "abc123") {
            UserIdPrincipal("jetbrains")
        } else {
            null
        }

}