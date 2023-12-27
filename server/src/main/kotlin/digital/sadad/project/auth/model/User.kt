package digital.sadad.project.auth.model

import java.time.LocalDateTime
import java.util.*

data class User(
    val id: Long?,
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val roleId: UUID,
    val avatar: String? = null,
    val active: Boolean = false,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)