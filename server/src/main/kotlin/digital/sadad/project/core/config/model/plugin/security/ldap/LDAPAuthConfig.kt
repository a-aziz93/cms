package digital.sadad.project.core.config.model.plugin.security.ldap

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

class LDAPAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val charset: String? = null,
    val ldapServerURL: String,
    val userDNFormat: String,
    session: SessionCookieConfig? = null,
) : AuthConfig(
    name,
    session,
)