package map.marker.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MarkerDto(
    val id: Long,
    val longitude: Double,
    val latitude: Double,
    val altitude: Double? = null,
    val description: String? = null,
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
