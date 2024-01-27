package digital.sadad.project.map.model.markerpopup.entity

import digital.sadad.project.cms.model.scan.entity.ScanTable
import map.model.markerpopup.entity.MarkerPopupEntity
import org.ufoss.kotysa.GenericTable

object MarkerPopupTable : GenericTable<MarkerPopupEntity>("marker_popups") {
    // Primary key
    val id = bigInt(MarkerPopupEntity::id)
        .identity()
        .primaryKey("PK_marker_popups")

    // Other fields
    val markerId = bigInt(MarkerPopupEntity::markerId)
        .foreignKey(ScanTable.id, "FK_marker_popups")
    val title = varchar(MarkerPopupEntity::title)

    // metadata
    val createdBy = varchar(MarkerPopupEntity::createdBy, "created_by")
    val createdAt = timestamp(MarkerPopupEntity::createdAt, "created_at")
    val updatedBy = varchar(MarkerPopupEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(MarkerPopupEntity::updatedAt, "updated_at")
}