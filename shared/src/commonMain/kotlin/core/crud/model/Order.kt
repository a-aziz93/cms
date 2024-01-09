package core.crud.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val name: String? = null,
    val ascending: Boolean = true,
    val nullFirst: Boolean = false,
)