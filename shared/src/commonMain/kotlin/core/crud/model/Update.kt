package core.crud.model

import core.crud.model.predicate.operation.Predicate

data class Update(
    val properties: Set<String>? = null,
    val predicate: Predicate? = null,
)