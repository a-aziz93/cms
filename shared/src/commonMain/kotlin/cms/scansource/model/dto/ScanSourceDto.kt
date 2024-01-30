package cms.scansource.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScanSourceDto(
    val id: Long? = null,
    val cameraServer: String,
    val cameraPort: Int,
    val cameraUser: String,
    val cameraPassword: String,
    val cameraDirPath: String,
    val cameraDirTemporalFormat: String,
    val cameraInDirectories: Boolean = true,
    val cameraFileNamePattern: String? = null,
    val xrayServer: String,
    val xrayPort: Int,
    val xrayUser: String,
    val xrayPassword: String,
    val xrayDirPath: String,
    val xrayDirTemporalFormat: String,
    val xrayInDirectories: Boolean = true,
    val xrayFileNamePattern: String? = null,
    val scalesServer: String,
    val scalesPort: Int,
    val scalesUser: String,
    val scalesPassword: String,
    val scalesDirPath: String,
    val scalesDirTemporalFormat: String,
    val scalesInDirectories: Boolean = true,
    val scalesFileNamePattern: String? = null,
    val mapperId: Long,
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
