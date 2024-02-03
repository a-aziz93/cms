package core.notification.model.dto

import core.notification.model.Notification
import core.notification.model.NotificationType
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NotificationCreateDto<T>(
    val id: Long?,
    val entity: String,
    val type: NotificationType,
    val data: T?,
)
