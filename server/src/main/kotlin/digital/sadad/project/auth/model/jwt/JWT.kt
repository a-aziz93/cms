package digital.sadad.project.auth.model.jwt

import auth.entity.user.UserEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.model.security.JWTConfig
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.*
import kotlin.time.DurationUnit

open class JWT(private val config: JWTConfig) {
    protected fun create(
        userEntity: UserEntity,
        roles: List<String>?,
        algorithm: Algorithm
    ): String {
        var jwt = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withSubject(SUBJECT)
            // user claims and other data to store
            .withClaim(USER_ID_CLAIM, userEntity.id.toString())
            .withClaim(USERNAME_CLAIM, userEntity.username)
            .withClaim(USER_EMAIL_CLAIM, userEntity.email)
        if (roles != null) {
            jwt = jwt.withClaim(USER_ROLES_CLAIM, roles)
        }
        if (config.expiration != null) {
            // expiration time from currentTimeMillis + (times in milliseconds)
            jwt =
                jwt.withExpiresAt(Date(System.currentTimeMillis() + config.expiration.toLong(DurationUnit.MILLISECONDS)))
        }
        // sign with secret
        return jwt
            .sign(algorithm)
    }

    fun validate(credential: JWTCredential): Principal? =
        if (credential.payload.audience.contains(config.audience) &&
            credential.payload.getClaim(USERNAME_CLAIM).asString().isNotEmpty()
        ) {
            JWTPrincipal(credential.payload)
        } else {
            null
        }

    companion object {
        const val SUBJECT = "Authentication"
        const val USER_ID_CLAIM = "userId"
        const val USERNAME_CLAIM = "username"
        const val USER_EMAIL_CLAIM = "userEmail"
        const val USER_ROLES_CLAIM = "roles"
    }
}