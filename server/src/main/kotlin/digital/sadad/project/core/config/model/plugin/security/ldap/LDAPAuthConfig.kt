package digital.sadad.project.core.config.model.plugin.security.ldap

import digital.sadad.project.core.config.model.plugin.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class LDAPAuthConfig(
    realm: String? = null,
    charset: String? = null,
    val ldapServerURL: String,
    val userDNFormat: String,
    session: SessionAuthConfig? = null,
) : BasicAuthConfig(realm, charset, session)