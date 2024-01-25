package core.crud.model.routerole.entity

import core.crud.model.routerole.RouteRole
import java.time.LocalDateTime

class RouteRoleEntity(
    val id: Long?,
    userId: Long,
    roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
) : RouteRole(
    userId,
    roleId,
)