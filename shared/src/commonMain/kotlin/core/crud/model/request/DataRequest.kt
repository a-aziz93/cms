package core.crud.model.request

import core.crud.model.request.predicate.Predicate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class DataRequest(
    val projections: List<String>? = null,
    val predicate: Predicate? = null,
    val page: Page? = null,
    val sort: List<Order>? = null,
)