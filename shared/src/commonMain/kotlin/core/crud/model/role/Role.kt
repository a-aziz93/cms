package core.crud.model.role

import java.time.LocalDateTime
import java.util.*

open class Role(
    val label: String,
    val description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
)