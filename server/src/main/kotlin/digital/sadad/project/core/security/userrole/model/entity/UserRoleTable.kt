package digital.sadad.project.core.security.userrole.model.entity

import core.security.userrole.model.entity.UserRoleEntity
import org.ufoss.kotysa.GenericTable

object UserRoleTable : GenericTable<UserRoleEntity>("user_roles") {
    // Primary key
    val id = bigInt(UserRoleEntity::id)
        .identity()
        .primaryKey("PK_user_role_bind")

    // Other fields
    val userId = bigInt(UserRoleEntity::userId)
        .foreignKey(digital.sadad.project.core.security.user.model.entity.UserTable.id, "FK_users")
    val roleId = bigInt(UserRoleEntity::roleId)
        .foreignKey(digital.sadad.project.core.security.role.model.entity.RoleTable.id, "FK_user_roles")

    // metadata
    val createdBy = varchar(UserRoleEntity::createdBy, "created_by")
    val createdAt = timestamp(UserRoleEntity::createdAt, "created_at")
    val updatedBy = varchar(UserRoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(UserRoleEntity::updatedAt, "updated_at")
}