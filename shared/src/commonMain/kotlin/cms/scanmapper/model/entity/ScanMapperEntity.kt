package cms.scanmapper.model.entity

import cms.scanmapper.model.ScanMapper
import cms.scanmapper.model.dto.ScanMapperCreateDto
import cms.scanmapper.model.dto.ScanMapperDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper

@KConMapper(
    toClasses = [ScanMapperCreateDto::class, ScanMapperDto::class],
    fromClasses = [ScanMapperCreateDto::class, ScanMapperDto::class],
)
class ScanMapperEntity(
    val id: Long? = null,
    plateNumber: String,
    val createdBy: String? = null,
    val createdAt: java.time.LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: java.time.LocalDateTime? = null,
) : ScanMapper(plateNumber)