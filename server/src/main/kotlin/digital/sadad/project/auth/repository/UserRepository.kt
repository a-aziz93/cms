package digital.sadad.project.auth.repository

import digital.sadad.project.auth.entity.RoleEntity
import digital.sadad.project.auth.entity.UserEntity
import digital.sadad.project.core.crud.repository.CRUDRepository

interface UserRepository : CRUDRepository<UserEntity, Long> {
    suspend fun save(vararg users: UserEntity, username: String?): List<UserEntity>
    suspend fun findWithRole(id: Long): Pair<UserEntity, RoleEntity>?
    suspend fun findWithRole(username: String): Pair<UserEntity, RoleEntity>?
    suspend fun findWithRole(username: String, password: String): Pair<UserEntity, RoleEntity>?
}
