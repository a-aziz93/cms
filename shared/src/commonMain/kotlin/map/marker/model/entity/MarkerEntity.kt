package map.marker.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import map.marker.model.Marker
import map.marker.model.dto.MarkerCreateDto
import map.marker.model.dto.MarkerDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [MarkerCreateDto::class, MarkerDto::class],
    fromClasses = [MarkerCreateDto::class, MarkerDto::class],
)
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