package digital.sadad.project.core.config.model.security

import kotlin.time.Duration

class JWTRS256Config(
    val privateKey: String,
    issuer: String,
    audience: String,
    realm: String,
    expiration: Duration? = null,
) : JWTConfig(
    issuer,
    audience,
    realm,
    expiration,
)