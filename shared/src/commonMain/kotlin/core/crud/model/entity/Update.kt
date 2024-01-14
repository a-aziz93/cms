package core.crud.model.entity

import core.crud.model.predicate.operation.Predicate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    val properties: Map<String, @Contextual Any?>? = null,
    val predicate: Predicate? = null,
)