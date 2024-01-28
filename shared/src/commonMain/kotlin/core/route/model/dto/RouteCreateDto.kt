package core.route.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RouteCreateDto(
    val uri: String,
    val authName: String? = null,
    val rbacAuthType: String? = null,
)
