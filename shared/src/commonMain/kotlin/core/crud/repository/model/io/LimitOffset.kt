package core.crud.repository.model.io

import kotlinx.serialization.Serializable

@Serializable
open class LimitOffset(
    val offset: Long? = null,
    val limit: Long? = null,
)
