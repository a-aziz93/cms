package core.crud.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import core.crud.repository.model.io.Order
import core.crud.repository.model.io.LimitOffset
import core.crud.repository.CRUDRepository
import core.crud.repository.model.transaction.Transaction
import core.expression.variable.BooleanVariable
import core.expression.variable.Variable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.Contextual

interface CRUDService<T : Any> {
    val crudRepository: CRUDRepository<T>

    suspend fun <R> transactional(
        byUser: String? = null, block: suspend CRUDRepository<T>.() -> R
    ): Result<R, Throwable> = runCatching { crudRepository.transactional(byUser, block) }

    suspend fun transactional(
        byUser: String? = null, transactions: Collection<Transaction>
    ): Result<List<Any>, Throwable> = runCatching { crudRepository.transactional(byUser, transactions) }

    suspend fun add(
        entities: Collection<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): Result<List<T>, Throwable> = runCatching { crudRepository.save(entities, updateIfExists, byUser) }

    suspend fun update(
        fieldValues: Map<String, @Contextual Any?>, predicate: BooleanVariable? = null, byUser: String? = null
    ): Result<List<Long>, Throwable> = runCatching { crudRepository.update(fieldValues, predicate, byUser) }

    suspend fun get(
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Result<Flow<T>, Throwable> = runCatching { crudRepository.find(sort, predicate, limitOffset) }

    suspend fun get(
        projections: Collection<Variable>,
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Result<Flow<List<Any?>>, Throwable> =
        runCatching { crudRepository.find(projections, sort, predicate, limitOffset) }

    suspend fun remove(predicate: BooleanVariable? = null): Result<Long, Throwable> =
        runCatching { crudRepository.delete(predicate) }

    suspend fun aggregate(
        aggregate: core.expression.aggregate.AggregateExpression,
        predicate: BooleanVariable? = null,
    ): Result<Number, Throwable> = runCatching { crudRepository.aggregate(aggregate, predicate) }
}