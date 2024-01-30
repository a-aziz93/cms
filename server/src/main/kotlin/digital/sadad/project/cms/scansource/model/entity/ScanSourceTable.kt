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
    val licensePlateServer = varchar(ScanSourceEntity::licensePlateServer)
        .unique()
    val licensePlatePort = integer(ScanSourceEntity::licensePlatePort)
    val licensePlateUser = varchar(ScanSourceEntity::licensePlateUser)
    val licensePlatePassword = varchar(ScanSourceEntity::licensePlatePassword)
    val licensePlateDirPath = varchar(ScanSourceEntity::licensePlateDirPath)
    val licensePlateTemporalFormat = varchar(ScanSourceEntity::licensePlateTemporalFormat)
    val licensePlateInDirectories = boolean(ScanSourceEntity::licensePlateInDirectories)

    val xrayServer = varchar(ScanSourceEntity::xrayServer)
        .unique()
    val xrayPort = integer(ScanSourceEntity::xrayPort)
    val xrayUser = varchar(ScanSourceEntity::xrayUser)
    val xrayPassword = varchar(ScanSourceEntity::xrayPassword)
    val xrayDirPath = varchar(ScanSourceEntity::xrayDirPath)
    val xrayTemporalFormat = varchar(ScanSourceEntity::xrayTemporalFormat)
    val xrayInDirectories = boolean(ScanSourceEntity::xrayInDirectories)

    val scalesServer = varchar(ScanSourceEntity::scalesServer)
        .unique()
    val scalesPort = integer(ScanSourceEntity::scalesPort)
    val scalesUser = varchar(ScanSourceEntity::scalesUser)
    val scalesPassword = varchar(ScanSourceEntity::scalesPassword)
    val scalesDirPath = varchar(ScanSourceEntity::scalesDirPath)
    val scalesTemporalFormat = varchar(ScanSourceEntity::scalesTemporalFormat)
    val scalesInDirectories = boolean(ScanSourceEntity::scalesInDirectories)

    val mapperId = bigInt(ScanSourceEntity::mapperId)
        .foreignKey(ScanMapperTable.id, "FK_source_mappers")

    // metadata
    val createdBy = varchar(ScanSourceEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanSourceEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanSourceEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanSourceEntity::updatedAt, "updated_at")
}