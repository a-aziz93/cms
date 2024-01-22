package digital.sadad.project.auth.service.security.ldap

import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import io.ktor.server.auth.*
import io.ktor.server.auth.ldap.*
import org.koin.core.annotation.Single

@Single
class LDAPAuthService(

) : SkipableAuthService, RBACAuthService {

    suspend fun validate(
        credential: UserPasswordCredential,
        ldapServerURL: String,
        userDNFormat: String
    ): Principal? =
        ldapAuthenticate(credential, ldapServerURL, userDNFormat)
}