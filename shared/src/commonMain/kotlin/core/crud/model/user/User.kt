package core.crud.model.user

import java.time.LocalDateTime
import java.util.*

open class User(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String? = null,
    val active: Boolean = false,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
)