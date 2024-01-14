package core.crud.model.entity

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val number: Int = 0,
    val size: Int = 1,
)
