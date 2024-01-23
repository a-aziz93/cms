package digital.sadad.project.core.config.model.plugin.security.jwt

import digital.sadad.project.core.config.model.plugin.security.jwt.JWTConfig
import kotlin.time.Duration

class JWTRS256Config(
    val privateKey: String,
    issuer: String,
    audience: String,
    realm: String,
    expiration: Duration? = null,
) : digital.sadad.project.core.config.model.plugin.security.jwt.JWTConfig(
    issuer,
    audience,
    realm,
    expiration,
)