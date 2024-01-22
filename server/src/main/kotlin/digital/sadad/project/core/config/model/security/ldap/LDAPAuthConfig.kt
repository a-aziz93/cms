package digital.sadad.project.core.config.model.security.ldap

import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class LDAPAuthConfig(
    realm: String? = null,
    charset: String? = null,
    val ldapServerURL: String,
    val userDNFormat: String
) : BasicAuthConfig(realm, charset)