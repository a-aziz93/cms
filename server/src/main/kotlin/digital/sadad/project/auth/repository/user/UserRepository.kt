package digital.sadad.project.auth.repository.user

import digital.sadad.project.auth.entity.role.RoleEntity
import digital.sadad.project.auth.entity.user.UserEntity
import digital.sadad.project.core.crud.repository.CRUDRepository

interface UserRepository : CRUDRepository<UserEntity, Long> {
    suspend fun save(vararg users: UserEntity, username: String?): List<UserEntity>
    suspend fun findWithRole(id: Long): Pair<UserEntity, RoleEntity>?
    suspend fun findWithRole(username: String): Pair<UserEntity, RoleEntity>?
    suspend fun findWithRole(username: String, password: String): Pair<UserEntity, RoleEntity>?
}