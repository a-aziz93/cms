package digital.sadad.project.core.config.model.plugin.security.jwt

import digital.sadad.project.core.config.model.plugin.security.jwt.JWTConfig
import kotlin.time.Duration

class JWTRS256Config(
    name: String? = null,
    val privateKey: String,
    issuer: String,
    audience: String,
    realm: String,
    expiration: Duration? = null,
) : JWTConfig(
    name,
    issuer,
    audience,
    realm,
    expiration,
)