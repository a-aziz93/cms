package digital.sadad.project.auth.model

import com.auth0.jwt.JWT as Jwt
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.model.JWTHS256Config

class JWTHS256(val config: JWTHS256Config) : JWT(config) {
    fun create(user: User): String = create(
        user,
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