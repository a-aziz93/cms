package core.crud.model.entity

import kotlinx.serialization.Serializable

@Serializable
open class LimitOffset(
    val offset: Long? = null,
    val limit: Long? = null,
)
