package digital.sadad.project.user.repository

import digital.sadad.project.common.crud.repository.CrudRepository
import digital.sadad.project.user.model.User

interface UsersRepository : CrudRepository<User, Long> {
    suspend fun findByUsername(username: String): User?
    fun hashedPassword(password: String): String
    suspend fun checkUserNameAndPassword(username: String, password: String): User?
}