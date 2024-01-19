package core.crud.service

import com.github.michaelbull.result.Result
import core.crud.model.entity.Order
import core.crud.model.entity.LimitOffset
import core.crud.model.entity.Update
import core.crud.model.entity.expression.predicate.Predicate
import kotlinx.coroutines.flow.Flow

interface CRUDService<T : Any, ID : Any> {
    suspend fun add(
        entities: List<T>,
        update: Update? = null,
        byUser: String? = null,
    ): Result<List<T>, Error>

    suspend fun get(
        predicate: core.crud.model.entity.expression.predicate.Predicate? = null,
        sort: List<Order>? = null,
        page: LimitOffset? = null,
    ): Result<Flow<T>, Error>

    suspend fun get(
        properties: List<String>,
        predicate: core.crud.model.entity.expression.predicate.Predicate? = null,
        sort: List<Order>? = null,
        page: LimitOffset? = null,
    ): Result<Flow<List<Any?>>, Error>

    suspend fun remove(predicate: core.crud.model.entity.expression.predicate.Predicate? = null): Result<Long, Error>

    suspend fun aggregate(predicate: core.crud.model.entity.expression.predicate.Predicate? = null): Result<Long, Error>
}