package digital.sadad.project.core.config.model.plugin.security.ldap

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class LDAPAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val charset: String? = null,
    val ldapServerURL: String,
    val userDNFormat: String,
    session: SessionAuthConfig? = null,
) : AuthConfig(
    name,
    session,
)