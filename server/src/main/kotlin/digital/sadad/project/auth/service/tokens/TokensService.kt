package digital.sadad.project.auth.service.tokens

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.auth.model.User
import digital.sadad.project.core.config.model.JWTConfig
import mu.two.KotlinLogging
import org.koin.core.annotation.Single
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Token Exception
 * @property message String
 */
sealed class TokenException(message: String) : RuntimeException(message) {
    class InvalidTokenException(message: String) : TokenException(message)
}


@Single
class TokensService(
    appConfig: AppConfig,
    private val jwtConfig: JWTConfig? = appConfig.envConfig.auth?.jwt
) {
    init {
        logger.debug { "Init tokens service with audience: ${jwtConfig?.audience}" }
    }

    /**
     * Generate a token JWT
     * @param user User
     * @return String
     */
    fun generateJWT(user: User): String {
        if (jwtConfig == null) {
            throw NullPointerException("jwtConfig")
        }

        val jwt = JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.issuer)
            .withSubject("Authentication")
            // user claims and other data to store
            .withClaim("username", user.username)
            .withClaim("userEmail", user.email)
            .withClaim("userId", user.id.toString())
        if (jwtConfig.expiration != null) {
            // expiration time from currentTimeMillis + (tiempo times in seconds) * 1000 (to millis)
            jwt.withExpiresAt(Date(System.currentTimeMillis() + jwtConfig.expiration.toLong() * 1000L))
        }
        // sign with secret
        return jwt.sign(
            Algorithm.HMAC512(jwtConfig.secret)
        )
    }

    /**
     * Verify a token JWT
     * @return JWTVerifier
     * @throws TokenException.InvalidTokenException
     */
    fun verifyJWT(): JWTVerifier {

        if (jwtConfig == null) {
            throw NullPointerException("jwtConfig")
        }

        return try {
            JWT.require(Algorithm.HMAC512(jwtConfig.secret))
                .withAudience(jwtConfig.audience)
                .withIssuer(jwtConfig.issuer)
                .build()
        } catch (e: Exception) {
            throw TokenException.InvalidTokenException("Invalid token")
        }
    }
}