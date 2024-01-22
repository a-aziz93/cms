package digital.sadad.project.auth.service.security.basic

import digital.sadad.project.auth.model.security.jwt.JWTHS256
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single

@Single
class BasicAuthService(

) : SkipableAuthService, RBACAuthService {

    val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }

    val hashedUserTable = UserHashedTableAuth(
        table = mapOf(
            "jetbrains" to digestFunction("foobar"),
            "admin" to digestFunction("password")
        ),
        digester = digestFunction
    )

    fun validate(credential: UserPasswordCredential): Principal? =
        hashedUserTable.authenticate(credential)

}