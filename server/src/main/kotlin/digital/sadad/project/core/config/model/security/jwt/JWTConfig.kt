package digital.sadad.project.core.config.model.security.jwt

import digital.sadad.project.core.config.model.security.AuthSchemesConfig
import digital.sadad.project.core.config.model.security.session.SessionAuthConfig
import kotlin.time.Duration

open class JWTConfig(
    val issuer: String,
    val audience: String,
    val realm: String? = null,
    val expiration: Duration? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
    val session: SessionAuthConfig? = null,
)
