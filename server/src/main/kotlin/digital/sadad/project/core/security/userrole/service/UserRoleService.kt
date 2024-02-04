package digital.sadad.project.core.security.userrole.service

import com.github.michaelbull.result.Result
import core.security.userrole.model.entity.UserRoleEntity
import core.crud.service.CRUDService

interface UserRoleService : CRUDService<UserRoleEntity> {

    suspend fun getUserRoles(userId: Long): Result<Set<String>, Throwable>
}