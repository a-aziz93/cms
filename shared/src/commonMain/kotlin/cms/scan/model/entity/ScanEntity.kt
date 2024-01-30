package cms.scan.model.entity

import cms.scan.model.Scan
import cms.scan.model.dto.ScanDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@KConMapper(
    toClasses = [ScanDto::class],
    fromClasses = [ScanDto::class],
)
class ScanEntity(
    val id: Long? = null,
    docNumber: String,
    vehicleLicensePlate: String,
    cameraVehicleLicensePlateImage: ByteArray,
    cameraTrailerLicensePlate: String? = null,
    cameraTrailerLicensePlateImage: ByteArray? = null,
    cameraDateTime: LocalDateTime,
    xrayCustomsCode: String,
    xrayOfficerName: String,
    xrayDriveName: String,
    xrayDriverLicenseImage: ByteArray,
    xrayVehicleModel: String,
    xrayVehicleСertificateImage: ByteArray? = null,
    xraySMRImage: ByteArray? = null,
    xrayConsignerName: String,
    xrayCountryDispatch: String,
    xrayControlType: String,
    xrayControlDescription: String,
    xrayImage: ByteArray,
    xrayDateTime: LocalDateTime,
    scalesGoodWeight: Double,
    scalesTruckWeight: Double,
    scalesTotalWeight: Double,
    scalesDateTime: LocalDateTime,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : Scan(
    docNumber,
    vehicleLicensePlate,
    cameraVehicleLicensePlateImage,
    cameraTrailerLicensePlate,
    cameraTrailerLicensePlateImage,
    cameraDateTime,
    xrayCustomsCode,
    xrayOfficerName,
    xrayDriveName,
    xrayDriverLicenseImage,
    xrayVehicleModel,
    xrayVehicleСertificateImage,
    xraySMRImage,
    xrayConsignerName,
    xrayCountryDispatch,
    xrayControlType,
    xrayControlDescription,
    xrayImage,
    xrayDateTime,
    scalesGoodWeight,
    scalesTruckWeight,
    scalesTotalWeight,
    scalesDateTime,
)