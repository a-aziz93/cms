package map.marker.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class MarkerCreateDto(
    val longitude: Double,
    val latitude: Double,
    val altitude: Double? = null,
    val description: String? = null,
)