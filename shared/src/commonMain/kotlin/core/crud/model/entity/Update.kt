package core.crud.model.entity

import core.crud.model.predicate.operation.Predicate
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    val properties: Set<String>? = null,
    val predicate: Predicate? = null,
)