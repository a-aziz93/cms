package digital.sadad.project.user.entity

import digital.sadad.project.user.model.User
import org.ufoss.kotysa.h2.H2Table
import org.ufoss.kotysa.oracle.OracleTable
import java.time.LocalDateTime

/**
 * User Table
 */
object UserTable : OracleTable<UserEntity>("users") {
    // Autoincrement and primary key
    val id = autoIncrementBigInt(UserEntity::id).primaryKey()

    // Other fields
    val name = varchar2(UserEntity::name)
    val email = varchar2(UserEntity::email)
    val username = varchar2(UserEntity::username)
    val password = varchar2(UserEntity::password)
    val avatar = varchar2(UserEntity::avatar)
    val role = varchar2(UserEntity::role)

    // metadata
    val createdAt = timestamp(UserEntity::createdAt, "created_at")
    val updatedAt = timestamp(UserEntity::updatedAt, "updated_at")
    val deleted = boolean(UserEntity::deleted)
}

/**
 * User Entity
 * We can use this class to map from Entity Row to Model and viceversa
 * We use it because we can't use the same class for both (avoid id nullable)
 * Or adapt some fields type to the database
 */
data class UserEntity(
    // Id
    val id: Long?, //

    // data
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String = User.DEFAULT_IMAGE,
    val role: String = User.Role.USER.name,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false
)