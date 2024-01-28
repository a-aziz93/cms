package cms.scan.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScanDto(
    val id: Long,
    val customsCode: String,
    val officerName: String,
    val driveName: String,
    val driverLicenseImage: ByteArray,
    val carModel: String,
    val carСertificateImage: ByteArray? = null,
    val carLicensePlate: String,
    val carLicansePlateImage: ByteArray,
    val trailerLicensePlate: String? = null,
    val smrImage: ByteArray,
    val consignerName: String,
    val countryDispatch: String,
    val goodWeight: Double,
    val truckWeight: Double,
    val totalWeight: Double,
    val controlType: String,
    val controlDescription: String,
    val xrayImage: ByteArray,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateTime: LocalDateTime,
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
