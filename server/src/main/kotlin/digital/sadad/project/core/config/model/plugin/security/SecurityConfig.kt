package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.PluginConfig
import digital.sadad.project.core.config.model.plugin.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.plugin.security.bearer.BearerAuthConfig
import digital.sadad.project.core.config.model.plugin.security.digest.DigestAuthConfig
import digital.sadad.project.core.config.model.plugin.security.form.FormAuthConfig
import digital.sadad.project.core.config.model.plugin.security.jwt.JWTHS256Config
import digital.sadad.project.core.config.model.plugin.security.jwt.JWTRS256Config
import digital.sadad.project.core.config.model.plugin.security.ldap.LDAPAuthConfig
import digital.sadad.project.core.config.model.plugin.security.oauth.OAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

class SecurityConfig(
    enable: Boolean? = null,
    val basic: Map<String, BasicAuthConfig>? = null,
    val digest: Map<String, DigestAuthConfig>? = null,
    val bearer: Map<String, BearerAuthConfig>? = null,
    val form: Map<String, FormAuthConfig>? = null,
    val session: Map<String, SessionAuthConfig>? = null,
    val ldap: Map<String, LDAPAuthConfig>? = null,
    val jwtHS256: Map<String, JWTHS256Config>? = null,
    val jwtRS256: Map<String, JWTRS256Config>? = null,
    val oauth: Map<String, OAuthConfig>? = null,
) : PluginConfig(enable)
