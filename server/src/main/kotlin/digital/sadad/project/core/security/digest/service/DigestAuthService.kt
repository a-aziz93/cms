package digital.sadad.project.core.security.digest.service

import core.expression.variable.extension.f
import digital.sadad.project.core.config.model.plugin.security.digest.DigestAuthConfig
import digital.sadad.project.core.security.model.UserIdPrincipalMetadata
import digital.sadad.project.core.security.user.repository.UserCRUDRepository
import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService
import digital.sadad.project.core.security.userrole.service.UserRoleService
import io.ktor.server.auth.*
import kotlinx.coroutines.flow.singleOrNull
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

class DigestAuthService(
    val config: DigestAuthConfig,
    val userCRUDRepository: UserCRUDRepository,
    val userRoleService: UserRoleService,
) : SkipableAuthService, RBACAuthService {

    suspend fun validate(credential: DigestCredential): Principal? =userCRUDRepository.transactional {
        val user = userCRUDRepository.find(predicate = "username".f().eq(credential.userName)).singleOrNull()



        if (credential.userName.isNotEmpty()) {
            CustomPrincipal(credential.userName, credential.realm)
        } else {
            null
        }
    }

    override fun roles(principal: Principal): Set<String> = (principal as UserIdPrincipalMetadata).roles ?: emptySet()
}