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
    val basic: Set<BasicAuthConfig>? = null,
    val digest: Set<DigestAuthConfig>? = null,
    val bearer: Set<BearerAuthConfig>? = null,
    val form: Set<FormAuthConfig>? = null,
    val session: Set<SessionAuthConfig>? = null,
    val ldap: Set<LDAPAuthConfig>? = null,
    val jwtHS256: Set<JWTHS256Config>? = null,
    val jwtRS256: Set<JWTRS256Config>? = null,
    val oauth: Set<OAuthConfig>? = null,
) : PluginConfig(enable)
