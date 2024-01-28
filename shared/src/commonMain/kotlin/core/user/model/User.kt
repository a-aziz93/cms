package core.user.model

import java.time.LocalDateTime
import java.util.*

open class User(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String? = null,
    val active: Boolean = false,
)