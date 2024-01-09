package core.crud.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PageRequest(
    val index: Int = 1,
    val size: Int = 1,
)
