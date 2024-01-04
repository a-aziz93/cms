package core.model.request

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    var name: String? = null,
    var ascending: Boolean = true,
    var nullFirst: Boolean = false,
)