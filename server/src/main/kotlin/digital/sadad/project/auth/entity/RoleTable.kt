package digital.sadad.project.auth.entity

import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import org.ufoss.kotysa.GenericTable

object RoleTable : GenericTable<Role>("roles") {
    // Primary key
    val id = bigInt(Role::id)
        .identity()
        .primaryKey("PK_roles")

    // Other fields
    val label = varchar(Role::label)
        .unique()
    val description = varchar(Role::description)

    // metadata
    val createdBy = varchar(Role::createdBy, "created_by")
    val createdAt = timestamp(Role::createdAt, "created_at")
    val updatedBy = varchar(Role::updatedBy, "updated_by")
    val updatedAt = timestamp(Role::updatedAt, "updated_at")
}