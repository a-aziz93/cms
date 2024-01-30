package cms.scansource.model.entity

import cms.scansource.model.ScanSource
import cms.scansource.model.dto.ScanSourceDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import java.time.LocalDateTime

@KConMapper(
    toClasses = [ScanSource::class, ScanSourceDto::class],
    fromClasses = [ScanSource::class, ScanSourceDto::class],
)
class ScanSourceEntity(
    val id: Long? = null,
    licensePlateServer: String,
    licensePlatePort: Int,
    licensePlateUser: String,
    licensePlatePassword: String,
    licensePlateDirPath: String,
    licensePlateTemporalFormat: String,
    licensePlateInDirectories: Boolean = true,
    licensePlateFileNamePattern: String? = null,
    xrayServer: String,
    xrayPort: Int,
    xrayUser: String,
    xrayPassword: String,
    xrayDirPath: String,
    xrayTemporalFormat: String,
    xrayInDirectories: Boolean = true,
    xrayFileNamePattern: String? = null,
    scalesServer: String,
    scalesPort: Int,
    scalesUser: String,
    scalesPassword: String,
    scalesDirPath: String,
    scalesTemporalFormat: String,
    scalesInDirectories: Boolean = true,
    scalesFileNamePattern: String? = null,
    mapperId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : ScanSource(
    licensePlateServer,
    licensePlatePort,
    licensePlateUser,
    licensePlatePassword,
    licensePlateDirPath,
    licensePlateTemporalFormat,
    licensePlateInDirectories,
    licensePlateFileNamePattern,
    xrayServer,
    xrayPort,
    xrayUser,
    xrayPassword,
    xrayDirPath,
    xrayTemporalFormat,
    xrayInDirectories,
    xrayFileNamePattern,
    scalesServer,
    scalesPort,
    scalesUser,
    scalesPassword,
    scalesDirPath,
    scalesTemporalFormat,
    scalesInDirectories,
    scalesFileNamePattern,
    mapperId
)