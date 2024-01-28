package cms.scansource.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScanSourceCreateDto(
    val url: String,
    val mapperId: Long,
)
