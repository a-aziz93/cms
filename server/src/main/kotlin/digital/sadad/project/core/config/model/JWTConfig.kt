package digital.sadad.project.core.config.model

import kotlin.time.Duration

data class JWTConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm: String,
    val expiration: Duration? = null,
)
