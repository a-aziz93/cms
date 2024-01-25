package core.crud.model.role.entity

import core.crud.model.role.Role
import java.time.LocalDateTime
import java.util.*

class RoleEntity(
    val id: Long?,
    label: String,
    description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : Role(
    label,
    description,
)