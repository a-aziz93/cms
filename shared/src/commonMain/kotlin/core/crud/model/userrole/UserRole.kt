package core.crud.model.userrole

import java.time.LocalDateTime
import java.util.*

open class UserRole(
    val userId: Long,
    val roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)