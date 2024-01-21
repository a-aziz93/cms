package digital.sadad.project.auth.service.basic

import digital.sadad.project.auth.model.jwt.JWTHS256
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single

@Single
class BasicAuthService(

) {

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