package digital.sadad.project.core.config.model.security.jwt

import digital.sadad.project.core.config.model.security.AuthSchemesConfig
import kotlin.time.Duration

open class JWTConfig(
    val issuer: String,
    val audience: String,
    val realm: String? = null,
    val expiration: Duration? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
)
