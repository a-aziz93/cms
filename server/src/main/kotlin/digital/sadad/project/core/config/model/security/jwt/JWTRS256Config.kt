package digital.sadad.project.core.config.model.security.jwt

import digital.sadad.project.core.config.model.security.jwt.JWTConfig
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