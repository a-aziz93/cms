package core.crud.model.role

import java.time.LocalDateTime
import java.util.*

open class Role(
    val label: String,
    val description: String? = null,
)