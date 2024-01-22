package digital.sadad.project.auth.service.security.basic

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.repository.user.UserRepository
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.basic.BasicAuthConfig
import io.ktor.server.auth.*
import io.ktor.util.*
import org.koin.core.annotation.Single

class BasicAuthService(
    val config: BasicAuthConfig,
    val userRepository: UserRepository,
) : SkipableAuthService, RBACAuthService {

    val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }

    val hashedUserTable = UserHashedTableAuth(
        table = mapOf(
            "jetbrains" to digestFunction("foobar"),
            "admin" to digestFunction("password")
        ),
        digester = digestFunction
    )

    suspend fun validate(credential: UserPasswordCredential): Principal? =
        hashedUserTable.authenticate(credential)

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}