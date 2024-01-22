package digital.sadad.project.auth.service.security.digest

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.repository.user.UserRepository
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import digital.sadad.project.core.config.model.security.digest.DigestAuthConfig
import io.ktor.server.auth.*
import org.koin.core.annotation.Single
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

class DigestAuthService(
    val config: DigestAuthConfig,
    val userRepository: UserRepository,
) : SkipableAuthService, RBACAuthService {

    fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

    val myRealm = "Access to the '/' path"
    val userTable: Map<String, ByteArray> = mapOf(
        "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
        "admin" to getMd5Digest("admin:$myRealm:password")
    )

    fun provider(username: String, realm: String): ByteArray? = userTable[username]

    suspend fun validate(credential: DigestCredential): Principal? =
        if (credential.userName.isNotEmpty()) {
            CustomPrincipal(credential.userName, credential.realm)
        } else {
            null
        }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}

data class CustomPrincipal(val userName: String, val realm: String) : Principal