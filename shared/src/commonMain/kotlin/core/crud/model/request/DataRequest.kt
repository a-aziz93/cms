package core.crud.model.request

import core.model.request.predicate.Predicate
import kotlinx.serialization.Serializable

@Serializable
data class DataRequest(
    var projections: List<String>? = null,
    var predicate: Predicate<*>? = null,
    var page: Page? = null,
    var sort: List<Order>? = null,
)