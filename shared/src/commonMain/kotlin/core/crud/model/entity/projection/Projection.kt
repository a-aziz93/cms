package core.crud.model.entity.projection

import kotlinx.serialization.Serializable

@Serializable
data class Projection(
    val property: String,
    val distinct: Boolean? = null,
)