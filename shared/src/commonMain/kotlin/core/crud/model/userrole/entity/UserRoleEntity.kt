package core.crud.model.userrole.entity

import core.crud.model.userrole.UserRole
import java.time.LocalDateTime
import java.util.*

class UserRoleEntity(
    val id: Long?,
    userId: Long,
    roleId: Long,
    createdBy: String? = null,
    createdAt: LocalDateTime? = null,
    updatedBy: String? = null,
    updatedAt: LocalDateTime? = null
) : UserRole(
    userId,
    roleId,
    createdBy,
    createdAt,
    updatedBy,
    updatedAt,
)