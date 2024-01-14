package core.crud.service

import com.github.michaelbull.result.Result
import core.crud.model.entity.Order
import core.crud.model.entity.Page
import core.crud.model.entity.Update
import core.crud.model.predicate.operation.Predicate
import kotlinx.coroutines.flow.Flow

interface CRUDService<T : Any, ID : Any> {
    suspend fun save(
        entities: List<T>,
        update: Update? = null,
        byUser: String? = null,
    ): Result<List<T>, Error>

    suspend fun find(
        predicate: Predicate? = null,
        sort: List<Order>? = null,
        page: Page? = null,
    ): Result<Flow<T>, Error>

    suspend fun find(
        properties: List<String>,
        predicate: Predicate? = null,
        sort: List<Order>? = null,
        page: Page? = null,
    ): Result<Flow<List<Any?>>, Error>

    suspend fun delete(predicate: Predicate? = null): Result<Long, Error>

    suspend fun count(predicate: Predicate? = null): Result<Long, Error>
}