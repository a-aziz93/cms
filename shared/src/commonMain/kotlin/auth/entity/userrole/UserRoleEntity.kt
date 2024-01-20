package auth.entity.userrole

import java.time.LocalDateTime
import java.util.*

data class UserRoleEntity(
    val id: Long?,
    val userId: Long,
    val roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)