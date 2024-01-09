package core.crud

import core.crud.model.Order
import core.crud.model.Page
import core.crud.model.PageData
import core.crud.model.predicate.Predicate
import kotlinx.coroutines.flow.Flow

interface CRUD<T : Any, ID : Any> {

    suspend fun save(
        entities: List<T>,
        updateIfExists: Boolean,
        byUsername: String?
    ): List<T>

    suspend fun find(id: ID): T?

    suspend fun find(
        projections: List<String>? = null,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
    ): Flow<T>

    suspend fun find(
        page: Page,
        projections: List<String>? = null,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
    ): PageData<T>

    suspend fun delete(id: ID): Boolean

    suspend fun delete(predicate: Predicate? = null): Long

    suspend fun count(predicate: Predicate): Long
}
