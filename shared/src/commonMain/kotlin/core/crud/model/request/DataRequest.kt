package core.crud.model.request

import core.crud.model.request.predicate.Predicate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class DataRequest(
    var projections: List<String>? = null,
    var predicate: Predicate<@Contextual Any>? = null,
    var page: Page? = null,
    var sort: List<Order>? = null,
)