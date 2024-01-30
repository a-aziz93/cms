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
    val cameraServer = varchar(ScanSourceEntity::cameraServer)
        .unique()
    val cameraPort = integer(ScanSourceEntity::cameraPort)
    val cameraUser = varchar(ScanSourceEntity::cameraUser)
    val cameraPassword = varchar(ScanSourceEntity::cameraPassword)
    val cameraDirPath = varchar(ScanSourceEntity::cameraDirPath)
    val cameraDirTemporalFormat = varchar(ScanSourceEntity::cameraDirTemporalFormat)
    val cameraInDirectories = boolean(ScanSourceEntity::cameraInDirectories)
    val cameraFileNamePattern = varchar(ScanSourceEntity::cameraFileNamePattern)

    val xrayServer = varchar(ScanSourceEntity::xrayServer)
        .unique()
    val xrayPort = integer(ScanSourceEntity::xrayPort)
    val xrayUser = varchar(ScanSourceEntity::xrayUser)
    val xrayPassword = varchar(ScanSourceEntity::xrayPassword)
    val xrayDirPath = varchar(ScanSourceEntity::xrayDirPath)
    val xrayDirTemporalFormat = varchar(ScanSourceEntity::xrayDirTemporalFormat)
    val xrayInDirectories = boolean(ScanSourceEntity::xrayInDirectories)
    val xrayFileNamePattern = varchar(ScanSourceEntity::xrayFileNamePattern)

    val scalesServer = varchar(ScanSourceEntity::scalesServer)
        .unique()
    val scalesPort = integer(ScanSourceEntity::scalesPort)
    val scalesUser = varchar(ScanSourceEntity::scalesUser)
    val scalesPassword = varchar(ScanSourceEntity::scalesPassword)
    val scalesDirPath = varchar(ScanSourceEntity::scalesDirPath)
    val scalesDirTemporalFormat = varchar(ScanSourceEntity::scalesDirTemporalFormat)
    val scalesInDirectories = boolean(ScanSourceEntity::scalesInDirectories)
    val scalesFileNamePattern = varchar(ScanSourceEntity::scalesFileNamePattern)

    val mapperId = bigInt(ScanSourceEntity::mapperId)
        .foreignKey(ScanMapperTable.id, "FK_source_mappers")

    // metadata
    val createdBy = varchar(ScanSourceEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanSourceEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanSourceEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanSourceEntity::updatedAt, "updated_at")
}