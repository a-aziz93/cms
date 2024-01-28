package cms.scansource.model

open class ScanSource(
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
)