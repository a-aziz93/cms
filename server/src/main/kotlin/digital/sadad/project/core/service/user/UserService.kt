package digital.sadad.project.core.service.user

import com.github.michaelbull.result.Result
import digital.sadad.project.auth.entity.role.RoleEntity
import digital.sadad.project.auth.entity.user.UserEntity
import core.error.Error

interface UserService {
    suspend fun save(vararg users: UserEntity, username: String?): Result<List<UserEntity>, Error>
    suspend fun findWithRole(id: Long): Result<Pair<UserEntity, RoleEntity>, Error>
    suspend fun findWithRole(username: String): Result<Pair<UserEntity, RoleEntity>, Error>
    suspend fun findWithRole(username: String, password: String): Result<Pair<UserEntity, RoleEntity>, Error>
    suspend fun delete(id: Long): Result<Boolean, Error>
    suspend fun delete(): Result<Long, Error>
}