package digital.sadad.project.core.security.service.jwt

import core.user.model.entity.UserEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.security.exception.TokenException

class JWTHS256Service(
    val config: digital.sadad.project.core.config.model.plugin.security.jwt.JWTHS256Config,
) : JWTAuthService(config) {
    fun create(
        userEntity: UserEntity,
        roles: List<String>? = null,
    ): String = create(
        userEntity,
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