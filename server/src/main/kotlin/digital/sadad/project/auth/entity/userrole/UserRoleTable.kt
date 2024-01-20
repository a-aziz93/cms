package digital.sadad.project.auth.entity.userrole

import digital.sadad.project.auth.entity.role.RoleTable
import digital.sadad.project.auth.entity.user.UserTable
import org.ufoss.kotysa.GenericTable

object UserRoleTable : GenericTable<UserRoleEntity>("user_role") {
    // Primary key
    val id = uuid(UserRoleEntity::id)
        .primaryKey("PK_role_bind")

    // Other fields
    val userId = uuid(UserRoleEntity::userId)
        .foreignKey(UserTable.id, "FK_users")
    val roleId = uuid(UserRoleEntity::roleId)
        .foreignKey(RoleTable.id, "FK_roles")

    // metadata
    val createdBy = varchar(UserRoleEntity::createdBy, "created_by")
    val createdAt = timestamp(UserRoleEntity::createdAt, "created_at")
    val updatedBy = varchar(UserRoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(UserRoleEntity::updatedAt, "updated_at")
}