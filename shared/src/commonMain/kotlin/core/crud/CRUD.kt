package core.crud

import core.crud.model.entity.Order
import core.crud.model.entity.LimitOffset
import core.crud.model.entity.Update
import core.crud.model.entity.expression.variable.BooleanVariable
import core.crud.model.entity.expression.variable.Variable
import core.crud.model.entity.expression.aggregate.Aggregate
import kotlinx.coroutines.flow.Flow

interface CRUD<T : Any> {
    suspend fun save(
        entities: Collection<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): List<T>

    suspend fun update(updates: Collection<Update>): List<Long>

    suspend fun find(
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<T>

    suspend fun find(
        projections: Collection<Variable>,
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Flow<List<Any?>>

    suspend fun delete(predicate: BooleanVariable? = null): Long

    suspend fun aggregate(
        aggregate: Aggregate,
        predicate: BooleanVariable? = null,
    ): Number
}
