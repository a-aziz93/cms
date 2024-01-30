package cms.scan.model

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
open class Scan(
    val docNumber: String,
    val vehicleLicensePlate: String,
    val cameraVehicleLicensePlateImage: ByteArray,
    val cameraTrailerLicensePlate: String? = null,
    val cameraTrailerLicensePlateImage: ByteArray? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val cameraDateTime: LocalDateTime,
    val xrayCustomsCode: String,
    val xrayOfficerName: String,
    val xrayDriveName: String,
    val xrayDriverLicenseImage: ByteArray,
    val xrayVehicleModel: String,
    val xrayVehicleСertificateImage: ByteArray? = null,
    val xraySMRImage: ByteArray? = null,
    val xrayConsignerName: String,
    val xrayCountryDispatch: String,
    val xrayControlType: String,
    val xrayControlDescription: String,
    val xrayImage: ByteArray,
    @Serializable(with = LocalDateTimeSerializer::class)
    val xrayDateTime: LocalDateTime,
    val scalesGoodWeight: Double,
    val scalesTruckWeight: Double,
    val scalesTotalWeight: Double,
    @Serializable(with = LocalDateTimeSerializer::class)
    val scalesDateTime: LocalDateTime,
)
