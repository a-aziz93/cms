package digital.sadad.project.core.service.security.form

import digital.sadad.project.core.service.security.ChallengeableAuthService
import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
import io.ktor.server.auth.*
import org.koin.core.annotation.Single

class FormAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.form.FormAuthConfig,
) : ChallengeableAuthService, SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: UserPasswordCredential): Principal? =
        if (credential.name == "jetbrains" && credential.password == "foobar") {
            UserIdPrincipal(credential.name)
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}