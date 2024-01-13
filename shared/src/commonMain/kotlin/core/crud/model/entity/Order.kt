package core.crud.model.entity

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val name: String,
    val ascending: Boolean = true,
    val nullFirst: Boolean = false,
)