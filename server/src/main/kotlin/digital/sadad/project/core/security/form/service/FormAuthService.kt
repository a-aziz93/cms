package digital.sadad.project.core.security.form.service

import digital.sadad.project.core.security.service.ChallengeableAuthService
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import io.ktor.server.auth.*

class FormAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.form.FormAuthConfig,
) : ChallengeableAuthService,
    SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: UserPasswordCredential): Principal? =
        if (credential.name == "jetbrains" && credential.password == "foobar") {
            UserIdPrincipal(credential.name)
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}