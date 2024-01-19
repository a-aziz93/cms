package core.crud.model.entity

import core.crud.model.entity.expression.BooleanVariable
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    val properties: Map<String, @Contextual Any?>,
    val predicate: BooleanVariable? = null,
)