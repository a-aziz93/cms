package digital.sadad.project.cms.scanmapper.model.entity

import cms.scanmapper.model.entity.ScanMapperEntity
import org.ufoss.kotysa.GenericTable

object ScanMapperTable : GenericTable<ScanMapperEntity>("scan_mappers") {
    // Primary key
    val id = bigInt(ScanMapperEntity::id)
        .identity()
        .primaryKey("PK_scan_mappers")

    // Other fields


    // metadata
    val createdBy = varchar(ScanMapperEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanMapperEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanMapperEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanMapperEntity::updatedAt, "updated_at")
}