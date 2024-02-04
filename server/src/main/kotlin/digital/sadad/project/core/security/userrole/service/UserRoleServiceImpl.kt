package digital.sadad.project.core.security.userrole.service

import com.github.michaelbull.result.Result
import core.expression.variable.extension.f
import digital.sadad.project.core.security.role.repository.RoleCRUDRepository
import digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toSet

class UserRoleServiceImpl(
    override val crudRepository: UserRoleCRUDRepository,
    val roleCRUDRepository: RoleCRUDRepository,
) : UserRoleService {
    override suspend fun getUserRoles(userId: Long): Result<Set<String>, Error> = transactional {
        crudRepository.find(predicate = "userid".f().eq(userId)).map {
            roleCRUDRepository.find(predicate = "id".f().eq(it.roleId)).single().name
        }.toSet()
    }
}