package auth.dto.user

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserDto(
    val id: Long?,
    val name: String,
    val email: String,
    val username: String,
    val roles: List<String>,
    val avatar: String?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?,
    val active: Boolean
)