package digital.sadad.project.core.security.basic.service

import com.github.michaelbull.result.get
import com.github.michaelbull.result.map
import core.expression.variable.extension.f
import digital.sadad.project.core.config.model.plugin.security.basic.BasicAuthConfig
import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import digital.sadad.project.core.security.user.repository.UserCRUDRepository
import digital.sadad.project.core.security.userrole.service.UserRoleService
import io.ktor.server.auth.*
import io.ktor.util.*
import kotlinx.coroutines.flow.singleOrNull
import java.nio.charset.Charset

class BasicAuthService(
    val config: BasicAuthConfig,
    val userCRUDRepository: UserCRUDRepository,
    val userRoleService: UserRoleService,
    private val digestFunction: ((String) -> ByteArray)? = config.digestFunction?.let { cfg -> getDigestFunction(cfg.algorithm) { cfg.salt } }
) : SkipableAuthService, RBACAuthService {

    val passwordsEquals: (String, String) -> Boolean = if (digestFunction == null) {
        { p1, p2 -> p1 == p2 }
    } else {
        { p1, p2 -> digestFunction.invoke(p1) contentEquals p2.toByteArray(Charset.forName(config.charset)) }
    }

    suspend fun validate(credential: UserPasswordCredential): Principal? = userCRUDRepository.transactional {
        val user = userCRUDRepository.find(predicate = "username".f().eq(credential.name)).singleOrNull()

        if (user != null && passwordsEquals(credential.password, user.password)) {
            userRoleService.getUserRoles(user.id).map { UserIdPrincipalMetadata(credential.name, it) }.get()
        }

        null
    }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()

}