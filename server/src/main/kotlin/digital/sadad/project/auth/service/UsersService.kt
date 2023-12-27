package digital.sadad.project.auth.service

import com.github.michaelbull.result.Result
import digital.sadad.project.auth.error.UserError
import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import kotlinx.coroutines.flow.Flow

interface UsersService {
    suspend fun save(vararg users: User, username: String?): Result<List<User>, UserError>
    suspend fun findWithRole(id: Long): Result<Pair<User, Role>, UserError>
    suspend fun findWithRole(username: String): Result<Pair<User, Role>, UserError>
    suspend fun findWithRole(username: String, password: String): Result<Pair<User, Role>, UserError>
    suspend fun delete(id: Long): Result<Boolean, UserError>
    suspend fun delete(): Result<Long, UserError>
}