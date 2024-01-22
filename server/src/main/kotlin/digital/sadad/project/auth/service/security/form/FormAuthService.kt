package digital.sadad.project.auth.service.security.form

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.service.security.ChallengeableAuthService
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.security.form.FormAuthConfig
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

class FormAuthService(
    val config: FormAuthConfig,
) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: UserPasswordCredential): Principal? =
        if (credential.name == "jetbrains" && credential.password == "foobar") {
            UserIdPrincipal(credential.name)
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}