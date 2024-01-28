package cms.scansource.model.entity

import cms.scansource.model.ScanSource
import cms.scansource.model.dto.ScanSourceCreateDto
import cms.scansource.model.dto.ScanSourceDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import java.time.LocalDateTime

@KConMapper(
    toClasses = [ScanSourceCreateDto::class, ScanSourceDto::class],
    fromClasses = [ScanSourceCreateDto::class, ScanSourceDto::class],
)
class ScanSourceEntity(
    val id: Long? = null,
    licensePlateHostname: String,
    licensePlateUser: String,
    licensePlatePassword: String,
    licensePlateDirPath: String,
    xrayHostname: String,
    xrayUser: String,
    xrayPassword: String,
    xrayDirPath: String,
    scalesHostname: String,
    scalesUser: String,
    scalesPassword: String,
    scalesDirPath: String,
    mapperId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : ScanSource(
    licensePlateHostname,
    licensePlateUser,
    licensePlatePassword,
    licensePlateDirPath,
    xrayHostname,
    xrayUser,
    xrayPassword,
    xrayDirPath,
    scalesHostname,
    scalesUser,
    scalesPassword,
    scalesDirPath,
    mapperId
)