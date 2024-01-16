package core.crud

import core.crud.model.entity.Order
import core.crud.model.entity.LimitOffset
import core.crud.model.entity.Update
import core.crud.model.entity.aggregate.AggregateOperation
import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.projection.Projection
import kotlinx.coroutines.flow.Flow

interface CRUD<T : Any, ID : Any> {
    suspend fun save(
        entities: List<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): List<T>

    suspend fun update(updates: List<Update>): List<Long>

    suspend fun find(
        sort: List<Order>? = null,
        predicate: Predicate? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<T>

    suspend fun find(
        projections: List<Projection>,
        sort: List<Order>? = null,
        predicate: Predicate? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<List<Any?>>

    suspend fun delete(predicate: Predicate? = null): Long

    suspend fun aggregate(
        operation: AggregateOperation,
        projection: Projection? = null,
        predicate: Predicate? = null,
    ): Number
}
