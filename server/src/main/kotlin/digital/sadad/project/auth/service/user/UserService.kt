package digital.sadad.project.auth.service.user

import com.github.michaelbull.result.Result
import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import core.error.IOError

interface UserService {
    suspend fun save(vararg users: User, username: String?): Result<List<User>, IOError>
    suspend fun findWithRole(id: Long): Result<Pair<User, Role>, IOError>
    suspend fun findWithRole(username: String): Result<Pair<User, Role>, IOError>
    suspend fun findWithRole(username: String, password: String): Result<Pair<User, Role>, IOError>
    suspend fun delete(id: Long): Result<Boolean, IOError>
    suspend fun delete(): Result<Long, IOError>
}