package digital.sadad.project.auth.entity

import java.time.LocalDateTime

data class RoleEntity(
    val id: Long?,
    val label: String,
    val description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
)