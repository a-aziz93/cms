package cms.scan.model

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
open class Scan(
    val docNumber: String,
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
)
