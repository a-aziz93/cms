package digital.sadad.project.auth.entity

import digital.sadad.project.auth.model.User
import org.ufoss.kotysa.GenericTable
import org.ufoss.kotysa.H2Tables
import org.ufoss.kotysa.h2.H2Table

object UserTable : H2Table<User>("users") {
    // Primary key
    val id = bigInt(User::id).identity().primaryKey("PK_users")

    // Other fields
    val name = varchar(User::name)
    val email = varchar(User::email)
    val username = varchar(User::username).unique()
    val password = varchar(User::password)
    val roleId = uuid(User::roleId)
        .foreignKey(RoleTable.id, "FK_users_roles")
    val avatar = varchar(User::avatar)
    val active = boolean(User::active)

    // Metadata
    val createdBy = varchar(User::createdBy, "created_by")
    val createdAt = timestamp(User::createdAt, "created_at")
    val updatedBy = varchar(User::updatedBy, "updated_by")
    val updatedAt = timestamp(User::updatedAt, "updated_at")
}