package cms.scanmapper.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScanMapperDto(
    val id: Long,
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
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
