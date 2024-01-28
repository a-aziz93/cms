package core.user.model.dto

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
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)