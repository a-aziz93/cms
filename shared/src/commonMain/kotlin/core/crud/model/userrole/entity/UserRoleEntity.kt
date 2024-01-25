package core.crud.model.userrole.entity

import core.crud.model.routerole.RouteRole
import core.crud.model.userrole.UserRole
import java.time.LocalDateTime

class UserRoleEntity(
    val id: Long?,
    userId: Long,
    roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
) : UserRole(
    userId,
    roleId,
)