package core.crud.repository

import core.crud.repository.model.io.Order
import core.crud.repository.model.io.LimitOffset
import core.crud.repository.model.transaction.Transaction
import core.crud.repository.model.transaction.Transaction.*
import core.expression.aggregate.AggregateExpression
import core.expression.variable.BooleanVariable
import core.expression.variable.Variable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.Contextual

interface CRUDRepository<T : Any> {

    suspend fun <R> transactional(byUser: String? = null, block: suspend CRUDRepository<T>.() -> R): R

    suspend fun transactional(byUser: String? = null, transactions: Collection<Transaction>) =
        transactional {
            withContext(Dispatchers.IO) {
                transactions.map {
                    when (it) {
                        is SaveTransaction<*> -> save(it.entities as Collection<T>, it.updateIfExists, byUser)
                        is UpdateTransaction -> update(it.fieldValues, it.predicate, byUser)
                        is FindTransaction -> {
                            if (it.projections == null) find(it.sort, it.predicate, it.limitOffset)
                            else find(it.projections, it.sort, it.predicate, it.limitOffset)
                        }

                        is DeleteTransaction -> delete(it.predicate)
                        is AggregateTransaction -> aggregate(it.aggregate, it.predicate)
                    }
                }
            }
        }

    suspend fun save(
        entities: Collection<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): List<T>

    suspend fun update(
        fieldValues: Map<String, @Contextual Any?>,
        predicate: BooleanVariable? = null,
        byUser: String? = null
    ): List<Long>

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
        aggregate: AggregateExpression,
        predicate: BooleanVariable? = null,
    ): Number
}
