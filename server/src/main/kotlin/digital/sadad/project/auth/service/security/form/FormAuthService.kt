package digital.sadad.project.auth.service.security.form

import digital.sadad.project.auth.service.security.ChallengeableAuthService
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

@Single
class FormAuthService(

) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: UserPasswordCredential): Principal? =
        if (credential.name == "jetbrains" && credential.password == "foobar") {
            UserIdPrincipal(credential.name)
        } else {
            null
        }

}