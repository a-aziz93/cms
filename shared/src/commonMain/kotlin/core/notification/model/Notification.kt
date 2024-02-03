package core.notification.model

import core.serializers.LocalDateTimeJson
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
open class Notification<T>(
    val entity: String,
    val type: NotificationType,
    val data: T?,
    val createdAt: LocalDateTimeJson = LocalDateTime.now()
)