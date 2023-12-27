package digital.sadad.project.auth.repository

import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import digital.sadad.project.core.crud.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    suspend fun save(vararg users: User, username: String?): List<User>
    suspend fun findWithRole(id: Long): Pair<User, Role>?
    suspend fun findWithRole(username: String): Pair<User, Role>?
    suspend fun findWithRole(username: String, password: String): Pair<User, Role>?
}
