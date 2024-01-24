package digital.sadad.project.core.service.security.basic

import digital.sadad.project.core.repository.user.UserRepository
import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService
import io.ktor.server.auth.*
import io.ktor.util.*

class BasicAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.basic.BasicAuthConfig,
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