package digital.sadad.project.auth.repository

import digital.sadad.project.auth.model.User
import digital.sadad.project.core.crud.repository.CrudRepository

interface UsersRepository : CrudRepository<User, Long> {
    suspend fun save(vararg users: User, username: String?): List<User>

    suspend fun find(username: String): User?
    suspend fun find(username: String, password: String): User?
}
