package cms.model.scan

import kotlinx.datetime.LocalDateTime

open class Scan(
    val dateTime: LocalDateTime,
    val officerName: String,
    val plateNamber: String,
    val trailerNumber: String? = null,
    val driveName: String,
    val sacanImage: ByteArray,
    val countryDispatch: String,
    val consignerName: String,
    val controlType: String,
    val controlDescription: String,
    val docNumber: String,// ????
    val content: String,// ????
    val carLicense: ByteArray? = null,
    val driverLicense: ByteArray,
    val smr: ByteArray,
    val customsCode: String,
    val carModel: String,
    val goodWeight: Double,
    val truckWeight: Double,
    val totalWeight: Double,
)
