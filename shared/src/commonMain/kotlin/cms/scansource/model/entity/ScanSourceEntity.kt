package cms.scansource.model.entity

import cms.scansource.model.ScanSource
import cms.scansource.model.dto.ScanSourceCreateDto
import cms.scansource.model.dto.ScanSourceDto
import com.github.yanneckreiss.kconmapper.annotations.KConMapper

@KConMapper(
    toClasses = [ScanSourceCreateDto::class, ScanSourceDto::class],
    fromClasses = [ScanSourceCreateDto::class, ScanSourceDto::class],
)
class ScanSourceEntity(
    val id: Long? = null,
    url: String,
    mapperId: Long,
    val createdBy: String? = null,
    val createdAt: java.time.LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: java.time.LocalDateTime? = null,
) : ScanSource(url, mapperId)