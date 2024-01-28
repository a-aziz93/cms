package digital.sadad.project.cms.scan.model.entity

import cms.scan.model.entity.ScanEntity
import kotlinx.datetime.LocalDateTime
import org.ufoss.kotysa.GenericTable

object ScanTable : GenericTable<ScanEntity>("scans") {
    // Primary key
    val id = bigInt(ScanEntity::id)
        .identity()
        .primaryKey("PK_scans")

    // Other fields
    val customsCode = varchar(ScanEntity::customsCode)
    val officerName = varchar(ScanEntity::officerName)
    val driveName = varchar(ScanEntity::driveName)
    val driverLicenseImage = binary(ScanEntity::driverLicenseImage)
    val carModel = varchar(ScanEntity::carModel)
    val carCertificateImage = binary(ScanEntity::carСertificateImage)
    val carLicensePlate = varchar(ScanEntity::carLicensePlate)
    val carLicansePlateImage = binary(ScanEntity::carLicansePlateImage)
    val trailerLicensePlate = varchar(ScanEntity::trailerLicensePlate)
    val smrImage = binary(ScanEntity::smrImage)
    val consignerName = varchar(ScanEntity::consignerName)
    val countryDispatch = varchar(ScanEntity::countryDispatch)
    val goodWeight = doublePrecision(ScanEntity::goodWeight)
    val truckWeight = doublePrecision(ScanEntity::truckWeight)
    val totalWeight = doublePrecision(ScanEntity::totalWeight)
    val controlType = varchar(ScanEntity::controlType)
    val controlDescription = varchar(ScanEntity::controlDescription)
    val xrayImage = binary(ScanEntity::xrayImage)
    val dateTime = timestamp(ScanEntity::dateTime)


    // metadata
    val createdBy = varchar(ScanEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanEntity::updatedAt, "updated_at")
}