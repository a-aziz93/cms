package core.model.request

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val index: Int = 1,
    val size: Int = 1,
)
