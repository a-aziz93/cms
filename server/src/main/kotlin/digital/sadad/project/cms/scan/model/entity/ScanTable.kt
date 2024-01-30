package digital.sadad.project.cms.scan.model.entity

import cms.scan.model.entity.ScanEntity
import core.serializers.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.ufoss.kotysa.GenericTable

object ScanTable : GenericTable<ScanEntity>("scans") {
    // Primary key
    val id = bigInt(ScanEntity::id)
        .identity()
        .primaryKey("PK_scans")

    // Other fields

    val docNumber = varchar(ScanEntity::docNumber)
    val vehicleLicensePlate = varchar(ScanEntity::vehicleLicensePlate)
    val cameraVehicleLicensePlateImage = binary(ScanEntity::cameraVehicleLicensePlateImage)
    val cameraTrailerLicensePlate = varchar(ScanEntity::cameraTrailerLicensePlate)
    val cameraTrailerLicensePlateImage = binary(ScanEntity::cameraTrailerLicensePlateImage)
    val cameraDateTime = timestamp(ScanEntity::cameraDateTime)
    val xrayCustomsCode = varchar(ScanEntity::xrayCustomsCode)
    val xrayOfficerName = varchar(ScanEntity::xrayOfficerName)
    val xrayDriveName = varchar(ScanEntity::xrayDriveName)
    val xrayDriverLicenseImage = binary(ScanEntity::xrayDriverLicenseImage)
    val xrayVehicleModel = varchar(ScanEntity::xrayVehicleModel)
    val xrayVehicleСertificateImage = binary(ScanEntity::xrayVehicleСertificateImage)
    val xraySMRImage = binary(ScanEntity::xraySMRImage)
    val xrayConsignerName = varchar(ScanEntity::xrayConsignerName)
    val xrayCountryDispatch = varchar(ScanEntity::xrayCountryDispatch)
    val xrayControlType = varchar(ScanEntity::xrayControlType)
    val xrayControlDescription = varchar(ScanEntity::xrayControlDescription)
    val xrayImage = binary(ScanEntity::xrayImage)
    val xrayDateTime = timestamp(ScanEntity::xrayDateTime)
    val scalesGoodWeight = doublePrecision(ScanEntity::scalesGoodWeight)
    val scalesTruckWeight = doublePrecision(ScanEntity::scalesTruckWeight)
    val scalesTotalWeight = doublePrecision(ScanEntity::scalesTotalWeight)
    val scalesDateTime = timestamp(ScanEntity::scalesDateTime)

    // metadata
    val createdBy = varchar(ScanEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanEntity::updatedAt, "updated_at")
}