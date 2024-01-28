package map.markerpopup.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class MarkerPopupCreateDto(
    val title: String,
    val markerId: Long,
)
