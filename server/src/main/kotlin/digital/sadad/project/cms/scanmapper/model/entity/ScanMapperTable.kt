package digital.sadad.project.cms.scanmapper.model.entity

import cms.scanmapper.model.entity.ScanMapperEntity
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.ufoss.kotysa.GenericTable
import java.time.LocalDateTime

object ScanMapperTable : GenericTable<ScanMapperEntity>("scan_mappers") {
    // Primary key
    val id = bigInt(ScanMapperEntity::id)
        .identity()
        .primaryKey("PK_scan_mappers")

    // Other fields
    val cameraVehicleLicensePlate = varchar(ScanMapperEntity::cameraVehicleLicensePlate)
    val cameraVehicleLicensePlateImage = varchar(ScanMapperEntity::cameraVehicleLicensePlateImage)
    val cameraTrailerLicensePlate = varchar(ScanMapperEntity::cameraTrailerLicensePlate)
    val cameraTrailerLicensePlateImage = varchar(ScanMapperEntity::cameraTrailerLicensePlateImage)
    val cameraDateTime = varchar(ScanMapperEntity::cameraDateTime)
    val cameraDateTimeFormat = varchar(ScanMapperEntity::cameraDateTimeFormat)
    val xrayVehicleLicensePlate = varchar(ScanMapperEntity::xrayVehicleLicensePlate)
    val xrayCustomsCode = varchar(ScanMapperEntity::xrayCustomsCode)
    val xrayOfficerName = varchar(ScanMapperEntity::xrayOfficerName)
    val xrayDriveName = varchar(ScanMapperEntity::xrayDriveName)
    val xrayDriverLicenseImage = varchar(ScanMapperEntity::xrayDriverLicenseImage)
    val xrayVehicleModel = varchar(ScanMapperEntity::xrayVehicleModel)
    val xrayVehicleСertificateImage = varchar(ScanMapperEntity::xrayVehicleСertificateImage)
    val xraySMRImage = varchar(ScanMapperEntity::xraySMRImage)
    val xrayConsignerName = varchar(ScanMapperEntity::xrayConsignerName)
    val xrayCountryDispatch = varchar(ScanMapperEntity::xrayCountryDispatch)
    val xrayControlType = varchar(ScanMapperEntity::xrayControlType)
    val xrayControlDescription = varchar(ScanMapperEntity::xrayControlDescription)
    val xrayImage = varchar(ScanMapperEntity::xrayImage)
    val xrayDateTime = varchar(ScanMapperEntity::xrayDateTime)
    val xrayDateTimeFormat = varchar(ScanMapperEntity::xrayDateTimeFormat)
    val scalesVehicleLicensePlate = varchar(ScanMapperEntity::scalesVehicleLicensePlate)
    val scalesGoodWeight = varchar(ScanMapperEntity::scalesGoodWeight)
    val scalesTruckWeight = varchar(ScanMapperEntity::scalesTruckWeight)
    val scalesTotalWeight = varchar(ScanMapperEntity::scalesTotalWeight)
    val scalesDateTime = varchar(ScanMapperEntity::scalesDateTime)
    val scalesDateTimeFormat = varchar(ScanMapperEntity::scalesDateTimeFormat)

    // metadata
    val createdBy = varchar(ScanMapperEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanMapperEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanMapperEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanMapperEntity::updatedAt, "updated_at")
}