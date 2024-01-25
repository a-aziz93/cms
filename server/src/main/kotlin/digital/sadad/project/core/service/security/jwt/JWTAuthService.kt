package digital.sadad.project.core.service.security.jwt

import core.crud.model.user.entity.UserEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.model.plugin.security.jwt.JWTConfig
import digital.sadad.project.core.service.security.ChallengeableAuthService
import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import java.util.*
import kotlin.time.DurationUnit

abstract class JWTAuthService(private val config: JWTConfig) : ChallengeableAuthService, SkipableAuthService,
    RBACAuthService {
    override fun roles(principal: Principal): Set<String> =
        (principal as JWTPrincipal).payload.claims?.get(USER_ROLES_CLAIM)?.asList(String::class.java)?.toSet()
            ?: emptySet()

    override suspend fun challenge(call: ApplicationCall, vararg args: Any) =
        call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")

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

    suspend fun validate(credential: JWTCredential): Principal? =
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