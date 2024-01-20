package digital.sadad.project.auth.entity.userrole

import auth.entity.userrole.UserRoleEntity
import digital.sadad.project.auth.entity.role.RoleTable
import digital.sadad.project.auth.entity.role.RoleTable.identity
import digital.sadad.project.auth.entity.user.UserTable
import org.ufoss.kotysa.GenericTable

object UserRoleTable : GenericTable<UserRoleEntity>("user_role") {
    // Primary key
    val id = bigInt(UserRoleEntity::id)
        .identity()
        .primaryKey("PK_role_bind")

    // Other fields
    val userId = bigInt(UserRoleEntity::userId)
        .foreignKey(UserTable.id, "FK_users")
    val roleId = bigInt(UserRoleEntity::roleId)
        .foreignKey(RoleTable.id, "FK_roles")

    // metadata
    val createdBy = varchar(UserRoleEntity::createdBy, "created_by")
    val createdAt = timestamp(UserRoleEntity::createdAt, "created_at")
    val updatedBy = varchar(UserRoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(UserRoleEntity::updatedAt, "updated_at")
}