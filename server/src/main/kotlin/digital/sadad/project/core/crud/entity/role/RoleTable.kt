package digital.sadad.project.core.crud.entity.role

import core.crud.model.role.entity.RoleEntity
import org.ufoss.kotysa.GenericTable

object RoleTable : GenericTable<RoleEntity>("roles") {
    // Primary key
    val id = bigInt(RoleEntity::id)
        .identity()
        .primaryKey("PK_roles")

    // Other fields
    val label = varchar(RoleEntity::label)
        .unique()
    val description = varchar(RoleEntity::description)

    // metadata
    val createdBy = varchar(RoleEntity::createdBy, "created_by")
    val createdAt = timestamp(RoleEntity::createdAt, "created_at")
    val updatedBy = varchar(RoleEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(RoleEntity::updatedAt, "updated_at")
}