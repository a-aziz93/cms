package cms.scan.model.entity

import cms.scan.model.Scan
import cms.scan.model.dto.ScanDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import kotlinx.datetime.LocalDateTime

@KConMapper(
    toClasses = [ScanDto::class],
    fromClasses = [ScanDto::class],
)
class ScanEntity(
    val id: Long?,
    dateTime: LocalDateTime,
    officerName: String,
    plateNamber: String,
    trailerNumber: String? = null,
    driveName: String,
    sacanImage: ByteArray,
    countryDispatch: String,
    consignerName: String,
    controlType: String,
    controlDescription: String,
    docNumber: String,// ????
    content: String,// ????
    carLicense: ByteArray? = null,
    driverLicense: ByteArray,
    smr: ByteArray,
    customsCode: String,
    carModel: String,
    goodWeight: Double,
    truckWeight: Double,
    totalWeight: Double,
    val createdBy: String? = null,
    val createdAt: java.time.LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: java.time.LocalDateTime? = null,
) : Scan(
    dateTime,
    officerName,
    plateNamber,
    trailerNumber,
    driveName,
    sacanImage,
    countryDispatch,
    consignerName,
    controlType,
    controlDescription,
    docNumber,
    content,
    carLicense,
    driverLicense,
    smr,
    customsCode,
    carModel,
    goodWeight,
    truckWeight,
    totalWeight
)