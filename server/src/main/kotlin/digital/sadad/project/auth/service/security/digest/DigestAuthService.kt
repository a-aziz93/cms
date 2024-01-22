package digital.sadad.project.auth.service.security.digest

import digital.sadad.project.auth.model.security.jwt.JWTHS256
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

@Single
class DigestAuthService(

) : SkipableAuthService, RBACAuthService {

    fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

    val myRealm = "Access to the '/' path"
    val userTable: Map<String, ByteArray> = mapOf(
        "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
        "admin" to getMd5Digest("admin:$myRealm:password")
    )

    fun provider(username: String, realm: String): ByteArray? = userTable[username]

    fun validate(credential: DigestCredential): Principal? =
        if (credential.userName.isNotEmpty()) {
            CustomPrincipal(credential.userName, credential.realm)
        } else {
            null
        }
}

data class CustomPrincipal(val userName: String, val realm: String) : Principal