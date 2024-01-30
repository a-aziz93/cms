package core.user.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
open class User(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String? = null,
    val active: Boolean = false,
)