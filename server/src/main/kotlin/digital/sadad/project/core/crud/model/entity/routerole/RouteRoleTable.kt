package digital.sadad.project.core.crud.model.entity.routerole

import core.crud.model.routerole.entity.RouteRoleEntity
import digital.sadad.project.core.crud.model.entity.role.RoleTable
import digital.sadad.project.core.crud.model.entity.route.RouteTable
import digital.sadad.project.core.crud.model.entity.user.UserTable
import org.ufoss.kotysa.GenericTable

object RouteRoleTable : GenericTable<RouteRoleEntity>("route_roles") {
    // Primary key
    val id = bigInt(RouteRoleEntity::id)
        .identity()
        .primaryKey("PK_route_role_bind")

    // Other fields
    val routeId = bigInt(RouteRoleEntity::routeId)
        .foreignKey(RouteTable.id, "FK_routes")
    val roleId = bigInt(RouteRoleEntity::roleId)
        .foreignKey(RoleTable.id, "FK_route_roles")

    // metadata
    val createdBy = varchar(RouteRoleEntity::createdBy, "created_by")
    val createdAt = timestamp(RouteRoleEntity::createdAt, "created_at")
    val updatedBy = varchar(RouteRoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(RouteRoleEntity::updatedAt, "updated_at")
}