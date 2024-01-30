package cms.scansource.model

import kotlinx.serialization.Serializable

@Serializable
open class ScanSource(
    val licensePlateServer: String,
    val licensePlatePort: Int,
    val licensePlateUser: String,
    val licensePlatePassword: String,
    val licensePlateDirPath: String,
    val licensePlateTemporalFormat: String,
    val licensePlateInDirectories: Boolean = true,
    val licensePlateFileNamePattern: String? = null,
    val xrayServer: String,
    val xrayPort: Int,
    val xrayUser: String,
    val xrayPassword: String,
    val xrayDirPath: String,
    val xrayTemporalFormat: String,
    val xrayInDirectories: Boolean = true,
    val xrayFileNamePattern: String? = null,
    val scalesServer: String,
    val scalesPort: Int,
    val scalesUser: String,
    val scalesPassword: String,
    val scalesDirPath: String,
    val scalesTemporalFormat: String,
    val scalesInDirectories: Boolean = true,
    val scalesFileNamePattern: String? = null,
    val mapperId: Long,
)