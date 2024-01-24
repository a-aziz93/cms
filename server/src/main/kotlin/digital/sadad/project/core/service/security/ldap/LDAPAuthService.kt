package digital.sadad.project.core.service.security.ldap

import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
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