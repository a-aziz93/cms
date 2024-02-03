package digital.sadad.project.core.security.route.model.entity

import core.security.route.model.entity.RouteEntity
import org.ufoss.kotysa.GenericTable

object RouteTable : GenericTable<RouteEntity>("routes") {
    // Primary key
    val id = bigInt(RouteEntity::id)
        .identity()
        .primaryKey("PK_routes")

    // Other fields
    val uri = varchar(RouteEntity::uri)
        .unique()
    val authName = varchar(RouteEntity::authName)
    val rbacAuthType = varchar(RouteEntity::rbacAuthType)

    // Metadata
    val createdBy = varchar(RouteEntity::createdBy, "created_by")
    val createdAt = timestamp(RouteEntity::createdAt, "created_at")
    val updatedBy = varchar(RouteEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(RouteEntity::updatedAt, "updated_at")
}