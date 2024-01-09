package core.crud.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class PageData<T : Any>(
    val items: List<@Contextual T>,
    val pagesCount: Int,
)
