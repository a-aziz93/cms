package core.routerole.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RouteRoleCreateDto(
    val userId: Long,
    val roleId: Long,
)
