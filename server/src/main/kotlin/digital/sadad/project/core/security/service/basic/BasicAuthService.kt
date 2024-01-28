package digital.sadad.project.core.security.service.basic

import core.expression.variable.extension.f
import digital.sadad.project.core.user.repository.UserRepository
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import io.ktor.server.auth.*
import io.ktor.util.*
import kotlinx.coroutines.flow.single

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

    suspend fun validate(credential: UserPasswordCredential): Principal? {
        val user = userRepository.find(predicate = "username".f().eq(credential.name)).single()

        if (user != null && digestFunction(credential.password) contentEquals digestFunction(user.password)) {
            return UserIdPrincipal(credential.name)
        }
    }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}