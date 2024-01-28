package map.markerpopup.model.dto

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MarkerPopupDto(
    val id: Long,
    val title: String,
    val markerId: Long,
    val createdBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)
