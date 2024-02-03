package digital.sadad.project.core.config.model.plugin.security.jwt

import kotlin.time.Duration

class JWTHS256Config(
    name: String? = null,
    val secret: String,
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