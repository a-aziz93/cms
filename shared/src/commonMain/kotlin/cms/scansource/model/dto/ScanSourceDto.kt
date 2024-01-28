package cms.scansource.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScanSourceDto(
    val id: Long? = null,
    val licensePlateHostname: String,
    val licensePlateUser: String,
    val licensePlatePassword: String,
    val licensePlateDirPath: String,
    val xrayHostname: String,
    val xrayUser: String,
    val xrayPassword: String,
    val xrayDirPath: String,
    val scalesHostname: String,
    val scalesUser: String,
    val scalesPassword: String,
    val scalesDirPath: String,
    val mapperId: Long,
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
