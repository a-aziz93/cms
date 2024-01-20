package digital.sadad.project.auth.entity.userrole

import java.time.LocalDateTime
import java.util.*

data class UserRoleEntity(
    val id: UUID?,
    val userId: UUID,
    val roleId: UUID,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)