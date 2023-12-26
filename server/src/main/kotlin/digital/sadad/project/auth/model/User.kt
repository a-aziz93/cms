package digital.sadad.project.auth.model

import java.time.LocalDateTime
import java.util.*

data class User(
    val id: Long?,
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String? = null,
    val roleId: UUID,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val active: Boolean = false
)