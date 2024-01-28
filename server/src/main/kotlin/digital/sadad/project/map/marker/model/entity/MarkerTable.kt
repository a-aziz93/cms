package digital.sadad.project.map.marker.model.entity

import map.marker.model.entity.MarkerEntity
import org.ufoss.kotysa.GenericTable

object MarkerTable : GenericTable<MarkerEntity>("markers") {
    // Primary key
    val id = bigInt(MarkerEntity::id)
        .identity()
        .primaryKey("PK_markers")

    // Other fields
    val longitude = doublePrecision(MarkerEntity::longitude)
    val latitude = doublePrecision(MarkerEntity::latitude)
    val altitude = doublePrecision(MarkerEntity::altitude)
    val description = varchar(MarkerEntity::description)

    // metadata
    val createdBy = varchar(MarkerEntity::createdBy, "created_by")
    val createdAt = timestamp(MarkerEntity::createdAt, "created_at")
    val updatedBy = varchar(MarkerEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(MarkerEntity::updatedAt, "updated_at")
}