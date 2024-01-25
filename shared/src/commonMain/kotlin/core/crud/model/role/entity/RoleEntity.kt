package core.crud.model.role.entity

import core.crud.model.role.Role
import java.time.LocalDateTime
import java.util.*

class RoleEntity(
    val id: Long?,
    label: String,
    description: String? = null,
    createdBy: String? = null,
    createdAt: LocalDateTime? = null,
    updatedBy: String? = null,
    updatedAt: LocalDateTime? = null,
) : Role(
    label,
    description,
    createdBy,
    createdAt,
    updatedBy,
    updatedAt,
)