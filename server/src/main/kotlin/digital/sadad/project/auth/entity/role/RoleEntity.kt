package digital.sadad.project.auth.entity.role

import java.time.LocalDateTime
import java.util.*

data class RoleEntity(
    val id: UUID?,
    val label: String,
    val description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
)