package digital.sadad.project.auth.service.security.ldap

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.security.ldap.LDAPAuthConfig
import io.ktor.server.auth.*
import io.ktor.server.auth.ldap.*
import org.koin.core.annotation.Single

class LDAPAuthService(
    val config: LDAPAuthConfig,
) : SkipableAuthService, RBACAuthService {

    suspend fun validate(
        credential: UserPasswordCredential,
        ldapServerURL: String,
        userDNFormat: String
    ): Principal? =
        ldapAuthenticate(credential, ldapServerURL, userDNFormat)

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}