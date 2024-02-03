package core.security.userrole.model.dto

import core.serializers.LocalDateTimeJson
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserRoleDto(
    val id: Long,
    val userId: Long,
    val roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTimeJson? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTimeJson? = null,
)
