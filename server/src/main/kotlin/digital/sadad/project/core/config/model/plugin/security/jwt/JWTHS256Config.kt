package digital.sadad.project.core.config.model.plugin.security.jwt

import kotlin.time.Duration

class JWTHS256Config(
    val secret: String,
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