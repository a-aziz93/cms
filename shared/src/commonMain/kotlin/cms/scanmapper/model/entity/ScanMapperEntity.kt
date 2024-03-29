package cms.scanmapper.model.entity

import cms.scanmapper.model.ScanMapper
import cms.scanmapper.model.dto.ScanMapperDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@KConMapper(
    toClasses = [ScanMapper::class, ScanMapperDto::class],
    fromClasses = [ScanMapper::class, ScanMapperDto::class],
)
class ScanMapperEntity(
    val id: Long? = null,
    cameraVehicleLicensePlate: String,
    cameraVehicleLicensePlateImage: String,
    cameraTrailerLicensePlate: String? = null,
    cameraTrailerLicensePlateImage: String? = null,
    cameraDateTime: String,
    cameraDateTimeFormat: String,
    xrayVehicleLicensePlate: String,
    xrayCustomsCode: String,
    xrayOfficerName: String,
    xrayDriveName: String,
    xrayDriverLicenseImage: String,
    xrayVehicleModel: String,
    xrayVehicleСertificateImage: String? = null,
    xraySMRImage: String? = null,
    xrayConsignerName: String,
    xrayCountryDispatch: String,
    xrayControlType: String,
    xrayControlDescription: String,
    xrayImage: String,
    xrayDateTime: String,
    xrayDateTimeFormat: String,
    scalesVehicleLicensePlate: String,
    scalesGoodWeight: String,
    scalesTruckWeight: String,
    scalesTotalWeight: String,
    scalesDateTime: String,
    scalesDateTimeFormat: String,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : ScanMapper(
    cameraVehicleLicensePlate,
    cameraVehicleLicensePlateImage,
    cameraTrailerLicensePlate,
    cameraTrailerLicensePlateImage,
    cameraDateTime,
    cameraDateTimeFormat,
    xrayVehicleLicensePlate,
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
    xrayDateTimeFormat,
    scalesVehicleLicensePlate,
    scalesGoodWeight,
    scalesTruckWeight,
    scalesTotalWeight,
    scalesDateTime,
    scalesDateTimeFormat,
)