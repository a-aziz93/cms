package map.marker.model

import kotlinx.serialization.Serializable

@Serializable
open class Marker(
    val longitude: Double,
    val latitude: Double,
    val altitude: Double? = null,
    val description: String? = null,
)