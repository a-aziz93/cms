package core.crud.model.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class DataResponse<T : Any>(
    val items: List<@Contextual T>,
    val pageCount: Int,
)
