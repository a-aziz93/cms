package core.crud.repository.model.io

import kotlinx.serialization.Serializable

@Serializable
sealed class LimitOffset(
    val offset: Long? = null,
    val limit: Long? = null,
) {
    @Serializable
    class Page(
        offset: Long,
        limit: Long,
    ) : LimitOffset(offset * limit, limit)
}
