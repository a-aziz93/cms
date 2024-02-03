package core.security.user.model.dto

import core.serializers.LocalDateTimeJson
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val username: String,
    val avatar: String? = null,
    val active: Boolean,
    val createdBy: String? = null,
    val createdAt: LocalDateTimeJson? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTimeJson? = null,
)