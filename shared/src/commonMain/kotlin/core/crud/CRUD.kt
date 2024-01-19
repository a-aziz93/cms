package core.crud

import core.crud.model.entity.Order
import core.crud.model.entity.LimitOffset
import core.crud.model.entity.Update
import core.crud.model.entity.expression.aggregate.AggregateExpression
import core.crud.model.entity.expression.predicate.Predicate
import core.crud.model.entity.projection.Projection
import kotlinx.coroutines.flow.Flow

interface CRUD<T : Any, ID : Any> {
    suspend fun save(
        entities: Collection<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): List<T>

    suspend fun update(updates: Collection<Update>): List<Long>

    suspend fun find(
        sort: Collection<Order>? = null,
        predicate: core.crud.model.entity.expression.predicate.Predicate? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<T>

    suspend fun find(
        projections: Collection<Projection>,
        sort: Collection<Order>? = null,
        predicate: core.crud.model.entity.expression.predicate.Predicate? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<List<Any?>>

    suspend fun delete(predicate: core.crud.model.entity.expression.predicate.Predicate? = null): Long

    suspend fun aggregate(
        operation: core.crud.model.entity.expression.aggregate.AggregateExpression,
        projection: Projection? = null,
        predicate: core.crud.model.entity.expression.predicate.Predicate? = null,
    ): Number
}
