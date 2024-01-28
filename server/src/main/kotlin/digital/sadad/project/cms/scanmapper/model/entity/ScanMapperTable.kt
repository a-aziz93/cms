package digital.sadad.project.cms.scanmapper.model.entity

import cms.scanmapper.model.entity.ScanMapperEntity
import org.ufoss.kotysa.GenericTable

object ScanMapperTable : GenericTable<ScanMapperEntity>("scan_mappers") {
    // Primary key
    val id = bigInt(ScanMapperEntity::id)
        .identity()
        .primaryKey("PK_scan_mappers")

    // Other fields
    val customsCode = varchar(ScanMapperEntity::customsCode)
    val officerName = varchar(ScanMapperEntity::officerName)
    val driveName = varchar(ScanMapperEntity::driveName)
    val driverLicense = varchar(ScanMapperEntity::driverLicense)
    val carModel = varchar(ScanMapperEntity::carModel)
    val carСertificate = varchar(ScanMapperEntity::carСertificate)
    val carLicensePlate = varchar(ScanMapperEntity::carLicensePlate)
    val carLicansePlateImage = varchar(ScanMapperEntity::carLicansePlateImage)
    val trailerLicensePlate = varchar(ScanMapperEntity::trailerLicensePlate)
    val smr = varchar(ScanMapperEntity::smr)
    val consignerName = varchar(ScanMapperEntity::consignerName)
    val countryDispatch = varchar(ScanMapperEntity::countryDispatch)
    val goodWeight = varchar(ScanMapperEntity::goodWeight)
    val truckWeight = varchar(ScanMapperEntity::truckWeight)
    val totalWeight = varchar(ScanMapperEntity::totalWeight)
    val controlType = varchar(ScanMapperEntity::controlType)
    val controlDescription = varchar(ScanMapperEntity::controlDescription)
    val xrayImage = varchar(ScanMapperEntity::xrayImage)

    // metadata
    val createdBy = varchar(ScanMapperEntity::createdBy, "created_by")
    val createdAt = timestamp(ScanMapperEntity::createdAt, "created_at")
    val updatedBy = varchar(ScanMapperEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(ScanMapperEntity::updatedAt, "updated_at")
}