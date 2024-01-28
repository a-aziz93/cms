package cms.scan.model.entity

import cms.scan.model.Scan
import cms.scan.model.dto.ScanDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import java.time.LocalDateTime

@KConMapper(
    toClasses = [ScanDto::class],
    fromClasses = [ScanDto::class],
)
class ScanEntity(
    val id: Long?,
    docNumber: String,
    customsCode: String,
    officerName: String,
    driveName: String,
    driverLicenseImage: ByteArray,
    carModel: String,
    carСertificateImage: ByteArray? = null,
    carLicensePlate: String,
    carLicansePlateImage: ByteArray,
    trailerLicensePlate: String? = null,
    smrImage: ByteArray,
    consignerName: String,
    countryDispatch: String,
    goodWeight: Double,
    truckWeight: Double,
    totalWeight: Double,
    controlType: String,
    controlDescription: String,
    xrayImage: ByteArray,
    dateTime: LocalDateTime,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : Scan(
    docNumber,
    customsCode,
    officerName,
    driveName,
    driverLicenseImage,
    carModel,
    carСertificateImage,
    carLicensePlate,
    carLicansePlateImage,
    trailerLicensePlate,
    smrImage,
    consignerName,
    countryDispatch,
    goodWeight,
    truckWeight,
    totalWeight,
    controlType,
    controlDescription,
    xrayImage,
    dateTime,
)