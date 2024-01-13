package core.crud.model.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class PageResult<T : Any>(
    val items: List<@Contextual T>,
    val totalCount: Int,
)
