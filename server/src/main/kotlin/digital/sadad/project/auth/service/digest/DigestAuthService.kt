package digital.sadad.project.auth.service.digest

import digital.sadad.project.auth.model.jwt.JWTHS256
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single

@Single
class DigestAuthService(

) {

    fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

    val myRealm = "Access to the '/' path"
    val userTable: Map<String, ByteArray> = mapOf(
        "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
        "admin" to getMd5Digest("admin:$myRealm:password")
    )

    fun provider(userName: String, realm: String) {

    }

    fun validate(credential: DigestCredential): Principal? =
        if (credential.userName.isNotEmpty()) {
            CustomPrincipal(credential.userName, credential.realm)
        } else {
            null
        }
}

data class CustomPrincipal(val userName: String, val realm: String) : Principal