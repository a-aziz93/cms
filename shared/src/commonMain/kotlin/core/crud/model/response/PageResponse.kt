package core.crud.model.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class PageResponse<T : Any>(
    val items: List<@Contextual T>,
    val pagesCount: Int,
)
