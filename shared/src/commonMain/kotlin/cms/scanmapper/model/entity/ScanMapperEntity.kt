package cms.scanmapper.model.entity

import cms.scanmapper.model.ScanMapper
import cms.scanmapper.model.dto.ScanMapperDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper

@KConMapper(
    toClasses = [ScanMapper::class, ScanMapperDto::class],
    fromClasses = [ScanMapper::class, ScanMapperDto::class],
)
class ScanMapperEntity(
    val id: Long? = null,
    customsCode: String,
    officerName: String,
    driveName: String,
    driverLicense: String,
    carModel: String,
    carСertificate: String,
    carLicensePlate: String,
    carLicansePlateImage: String,
    trailerLicensePlate: String,
    smr: String,
    consignerName: String,
    countryDispatch: String,
    goodWeight: String,
    truckWeight: String,
    totalWeight: String,
    controlType: String,
    controlDescription: String,
    xrayImage: String,
    val createdBy: String? = null,
    val createdAt: java.time.LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: java.time.LocalDateTime? = null,
) : ScanMapper(
    customsCode,
    officerName,
    driveName,
    driverLicense,
    carModel,
    carСertificate,
    carLicensePlate,
    carLicansePlateImage,
    trailerLicensePlate,
    smr,
    consignerName,
    countryDispatch,
    goodWeight,
    truckWeight,
    totalWeight,
    controlType,
    controlDescription,
    xrayImage
)