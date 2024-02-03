package cms.scanmapper.model

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
open class ScanMapper(
    val cameraVehicleLicensePlate: String,
    val cameraVehicleLicensePlateImage: String,
    val cameraTrailerLicensePlate: String? = null,
    val cameraTrailerLicensePlateImage: String? = null,
    val cameraDateTime: String,
    val cameraDateTimeFormat: String,
    val xrayVehicleLicensePlate: String,
    val xrayCustomsCode: String,
    val xrayOfficerName: String,
    val xrayDriveName: String,
    val xrayDriverLicenseImage: String,
    val xrayVehicleModel: String,
    val xrayVehicleСertificateImage: String? = null,
    val xraySMRImage: String? = null,
    val xrayConsignerName: String,
    val xrayCountryDispatch: String,
    val xrayControlType: String,
    val xrayControlDescription: String,
    val xrayImage: String,
    val xrayDateTime: String,
    val xrayDateTimeFormat: String,
    val scalesVehicleLicensePlate: String,
    val scalesGoodWeight: String,
    val scalesTruckWeight: String,
    val scalesTotalWeight: String,
    val scalesDateTime: String,
    val scalesDateTimeFormat: String,
)