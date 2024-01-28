package cms.scanmapper.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScanMapperCreateDto(
    val plateNumber: String,
)
