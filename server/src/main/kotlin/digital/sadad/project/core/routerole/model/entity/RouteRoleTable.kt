package digital.sadad.project.core.routerole.model.entity

import core.routerole.model.entity.RouteRoleEntity
import digital.sadad.project.core.role.model.entity.RoleTable
import digital.sadad.project.core.route.model.entity.RouteTable
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