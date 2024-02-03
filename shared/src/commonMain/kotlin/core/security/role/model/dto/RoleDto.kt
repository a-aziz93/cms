package core.security.role.model.dto

import core.serializers.LocalDateTimeJson
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class RoleDto(
    val id: Long,
    val name: String,
    val description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTimeJson? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTimeJson? = null,
)
