package digital.sadad.project.core.config.model

data class JWTConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm: String,
    val expiration: String? = null,
)
