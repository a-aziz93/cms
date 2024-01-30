package core.role.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
open class Role(
    val name: String,
    val description: String? = null,
)