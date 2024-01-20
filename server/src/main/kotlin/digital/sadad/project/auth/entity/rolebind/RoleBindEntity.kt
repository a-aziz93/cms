package digital.sadad.project.auth.entity.rolebind

import java.time.LocalDateTime
import java.util.*

data class RoleBindEntity(
    val id: UUID?,
    val userId: UUID,
    val roleId: UUID,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)