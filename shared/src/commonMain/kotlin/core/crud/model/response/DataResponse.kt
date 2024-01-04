package core.crud.model.response

import kotlinx.serialization.Serializable

@Serializable
class DataResponse<T : Any>(
    items: List<T>,
    pageCount: Int,
)
