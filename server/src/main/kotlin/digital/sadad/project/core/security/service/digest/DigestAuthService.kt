package digital.sadad.project.core.security.service.digest

import digital.sadad.project.core.config.model.plugin.security.digest.DigestAuthConfig
import digital.sadad.project.core.user.repository.UserRepository
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import io.ktor.server.auth.*
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

    // a. HA1 = MD5(username:realm:password)
    // This part is stored on a server and can be used by Ktor to validate user credentials.
    // b. HA2 = MD5(method:digestURI)
    // c. response = MD5(HA1:nonce:HA2)
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