package auth.dto.notification

import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NotificationDto<T>(
    val entity: String,
    val type: NotificationType,
    val id: Long?,
    val data: T?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    enum class NotificationType { CREATE, UPDATE, DELETE }
}
