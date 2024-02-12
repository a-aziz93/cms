package digital.sadad.project.core.config.model.plugin.security.jwt

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.AuthSchemesConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig
import kotlin.time.Duration

open class JWTConfig(
    name: String? = null,
    val issuer: String,
    val audience: String,
    val realm: String? = null,
    val expiration: Duration? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
    session: SessionCookieConfig? = null,
) : AuthConfig(
    name,
    session,
)
