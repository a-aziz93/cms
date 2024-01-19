package core.crud.model.entity.expression.projection

import kotlinx.serialization.Serializable

@Serializable
data class Projection(
    val property: String,
    val distinct: Boolean? = null,
)