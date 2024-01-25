package digital.sadad.project.core.crud.model.entity.userrole

import core.crud.model.routerole.entity.RouteRoleEntity
import core.crud.model.userrole.entity.UserRoleEntity
import digital.sadad.project.core.crud.model.entity.role.RoleTable
import digital.sadad.project.core.crud.model.entity.user.UserTable
import org.h2.engine.User
import org.ufoss.kotysa.GenericTable

object UserRoleTable : GenericTable<UserRoleEntity>("user_roles") {
    // Primary key
    val id = bigInt(UserRoleEntity::id)
        .identity()
        .primaryKey("PK_user_role_bind")

    // Other fields
    val userId = bigInt(UserRoleEntity::userId)
        .foreignKey(UserTable.id, "FK_users")
    val roleId = bigInt(UserRoleEntity::roleId)
        .foreignKey(RoleTable.id, "FK_user_roles")

    // metadata
    val createdBy = varchar(UserRoleEntity::createdBy, "created_by")
    val createdAt = timestamp(UserRoleEntity::createdAt, "created_at")
    val updatedBy = varchar(UserRoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(UserRoleEntity::updatedAt, "updated_at")
}