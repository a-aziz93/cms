package digital.sadad.project.core.config.model.security

import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.security.bearer.BearerAuthConfig
import digital.sadad.project.core.config.model.security.digest.DigestAuthConfig
import digital.sadad.project.core.config.model.security.form.FormAuthConfig
import digital.sadad.project.core.config.model.security.jwt.JWTHS256Config
import digital.sadad.project.core.config.model.security.jwt.JWTRS256Config
import digital.sadad.project.core.config.model.security.ldap.LDAPAuthConfig
import digital.sadad.project.core.config.model.security.oauth.OAuthProviderConfig
import digital.sadad.project.core.config.model.security.session.SessionAuthConfig

data class AuthConfig(
    val basic: Map<String, BasicAuthConfig>? = null,
    val digest: Map<String, DigestAuthConfig>? = null,
    val bearer: Map<String, BearerAuthConfig>? = null,
    val form: Map<String, FormAuthConfig>? = null,
    val session: Map<String, SessionAuthConfig>? = null,
    val ldap: Map<String, LDAPAuthConfig>? = null,
    val jwtHS256: Map<String, JWTHS256Config>? = null,
    val jwtRS256: Map<String, JWTRS256Config>? = null,
    val oauth: Map<String, OAuthProviderConfig>? = null,
)
