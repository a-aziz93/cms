package digital.sadad.project.cms.scansource.model.entity

import cms.scansource.model.entity.ScanSourceEntity
import digital.sadad.project.cms.scanmapper.model.entity.ScanMapperTable
import org.ufoss.kotysa.GenericTable

object ScanSourceTable : GenericTable<ScanSourceEntity>("scan_sources") {
    // Primary key
    val id = bigInt(ScanSourceEntity::id)
        .identity()
        .primaryKey("PK_scan_sources")

    // Other fields
    val licensePlateHostname = varchar(ScanSourceEntity::licensePlateHostname)
        .unique()
    val licensePlateUser = varchar(ScanSourceEntity::licensePlateUser)
    val licensePlatePassword = varchar(ScanSourceEntity::licensePlatePassword)
    val licensePlateDirPath = varchar(ScanSourceEntity::licensePlateDirPath)
    val xrayHostname = varchar(ScanSourceEntity::xrayHostname)
        .unique()
    val xrayUser = varchar(ScanSourceEntity::xrayUser)
    val xrayPassword = varchar(ScanSourceEntity::xrayPassword)
    val xrayDirPath = varchar(ScanSourceEntity::xrayDirPath)
    val scalesHostname = varchar(ScanSourceEntity::scalesHostname)
        .unique()
    val scalesUser = varchar(ScanSourceEntity::scalesUser)
    val scalesPassword = varchar(ScanSourceEntity::scalesPassword)
    val scalesDirPath = varchar(ScanSourceEntity::scalesDirPath)
    val mapperId = bigInt(ScanSourceEntity::mapperId)
        .foreignKey(ScanMapperTable.id, "FK_source_mappers")

    // metadata
    val createdBy = varchar(ScanSourceEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanSourceEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanSourceEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanSourceEntity::updatedAt, "updated_at")
}