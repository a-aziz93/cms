package digital.sadad.project.core.security.jwt.service

import core.security.user.model.entity.UserEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.model.plugin.security.jwt.JWTHS256Config
import digital.sadad.project.core.security.exception.TokenException

class JWTHS256Service(
    val config: JWTHS256Config,
) : JWTAuthService(config) {
    fun create(
        username: String,
        roles: List<String>? = null,
    ): String = create(
        username,
        roles,
        Algorithm.HMAC512(config.secret)
    )

    fun verify(): JWTVerifier {
        return try {
            JWT.require(Algorithm.HMAC512(config.secret))
                .withAudience(config.audience)
                .withIssuer(config.issuer)
                .build()
        } catch (e: Exception) {
            throw TokenException.InvalidTokenException("Invalid token")
        }
    }
}