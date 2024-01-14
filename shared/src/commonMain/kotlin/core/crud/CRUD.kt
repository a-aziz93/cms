package core.crud

import core.crud.model.entity.Order
import core.crud.model.entity.Page
import core.crud.model.entity.PageResult
import core.crud.model.entity.Update
import core.crud.model.predicate.operation.Predicate
import kotlinx.coroutines.flow.Flow

interface CRUD<T : Any, ID : Any> {
    suspend fun save(
        entities: List<T>,
        update: Update? = null,
        byUser: String? = null,
    ): List<T>

    suspend fun find(
        predicate: Predicate? = null,
        sort: List<Order>? = null,
        offset: Long? = null,
        limit: Long? = null,
    ): Flow<T>

    suspend fun find(
        properties: List<String>,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
        offset: Long? = null,
        limit: Long? = null,
    ): Flow<List<Any?>>

    suspend fun delete(predicate: Predicate? = null): Long

    suspend fun count(predicate: Predicate? = null): Long
}
