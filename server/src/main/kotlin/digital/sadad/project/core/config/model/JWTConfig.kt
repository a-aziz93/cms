package digital.sadad.project.core.config.model

import kotlin.time.Duration

open class JWTConfig(
    val issuer: String,
    val audience: String,
    val realm: String,
    val expiration: Duration? = null,
)
