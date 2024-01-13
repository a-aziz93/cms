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
        properties: List<String>? = null,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
    ): Flow<T>

    suspend fun find(
        page: Page,
        properties: List<String>? = null,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
    ): PageResult<T>

    suspend fun delete(predicate: Predicate? = null): Long

    suspend fun count(predicate: Predicate? = null): Long
}
