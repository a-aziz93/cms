package map.model.marker.entity

import map.model.marker.Marker
import java.time.LocalDateTime

class MarkerEntity(
    val id: Long? = null,
    longitude: Double,
    latitude: Double,
    altitude: Double? = null,
    description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : Marker(
    longitude,
    latitude,
    altitude,
    description,
)