package digital.sadad.project.auth.entity.rolebind

import digital.sadad.project.auth.entity.role.RoleTable
import digital.sadad.project.auth.entity.user.UserEntity
import digital.sadad.project.auth.entity.user.UserTable
import digital.sadad.project.auth.entity.user.UserTable.foreignKey
import org.ufoss.kotysa.GenericTable

object RoleBindTable : GenericTable<RoleBindEntity>("rolebind") {
    // Primary key
    val id = uuid(RoleBindEntity::id)
        .primaryKey("PK_role_bind")

    // Other fields
    val userId = uuid(RoleBindEntity::userId)
        .foreignKey(UserTable.id, "FK_users")
    val roleId = uuid(RoleBindEntity::roleId)
        .foreignKey(RoleTable.id, "FK_roles")

    // metadata
    val createdBy = varchar(RoleBindEntity::createdBy, "created_by")
    val createdAt = timestamp(RoleBindEntity::createdAt, "created_at")
    val updatedBy = varchar(RoleBindEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(RoleBindEntity::updatedAt, "updated_at")
}