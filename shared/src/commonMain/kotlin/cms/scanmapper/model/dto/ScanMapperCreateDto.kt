package cms.scanmapper.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScanMapperCreateDto(
    val customsCode: String,
    val officerName: String,
    val driveName: String,
    val driverLicense: String,
    val carModel: String,
    val carСertificate: String,
    val carLicensePlate: String,
    val carLicansePlateImage: String,
    val trailerLicensePlate: String,
    val smr: String,
    val consignerName: String,
    val countryDispatch: String,
    val goodWeight: String,
    val truckWeight: String,
    val totalWeight: String,
    val controlType: String,
    val controlDescription: String,
    val xrayImage: String,
)
