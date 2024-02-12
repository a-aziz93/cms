package digital.sadad.project.core.security.ldap.service

import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import io.ktor.server.auth.*
import io.ktor.server.auth.ldap.*

class LDAPAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.ldap.LDAPAuthConfig,
) : SkipableAuthService, RBACAuthService {

    suspend fun validate(
        credential: UserPasswordCredential,
        ldapServerURL: String,
        userDNFormat: String
    ): Principal? =
        ldapAuthenticate(credential, ldapServerURL, userDNFormat)

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}