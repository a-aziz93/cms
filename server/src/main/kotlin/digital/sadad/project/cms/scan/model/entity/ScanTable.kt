package digital.sadad.project.cms.scan.model.entity

import cms.scan.model.entity.ScanEntity
import org.ufoss.kotysa.GenericTable

object ScanTable : GenericTable<ScanEntity>("scans") {
    // Primary key
    val id = bigInt(ScanEntity::id)
        .identity()
        .primaryKey("PK_scans")

    // Other fields


    // metadata
    val createdBy = varchar(ScanEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanEntity::updatedAt, "updated_at")
}