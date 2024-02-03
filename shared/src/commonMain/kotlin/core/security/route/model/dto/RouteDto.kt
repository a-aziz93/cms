package core.security.route.model.dto

import core.serializers.LocalDateTimeJson
import core.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class RouteDto(
    val id: Long,
    val uri: String,
    val authName: String? = null,
    val rbacAuthType: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTimeJson? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTimeJson? = null,
)