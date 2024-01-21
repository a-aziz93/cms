package digital.sadad.project.auth.model.jwt

import auth.entity.user.UserEntity
import com.auth0.jwt.JWT as Jwt
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.auth.error.token.TokenException
import digital.sadad.project.core.config.model.security.JWTHS256Config

class JWTHS256(val config: JWTHS256Config) : JWT(config) {
    fun create(
        userEntity: UserEntity,
        roles: List<String>? = null,
    ): String = create(
        userEntity,
        roles,
        Algorithm.HMAC512(config.secret)
    )

    fun verifyJWT(): JWTVerifier {
        return try {
            Jwt.require(Algorithm.HMAC512(config.secret))
                .withAudience(config.audience)
                .withIssuer(config.issuer)
                .build()
        } catch (e: Exception) {
            throw TokenException.InvalidTokenException("Invalid token")
        }
    }
}