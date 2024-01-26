package core.crud.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
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
        byUser: String? = null,
        block: suspend CRUDRepository<T>.() -> R
    ): Result<R, Error> =
        Ok(crudRepository.transactional(byUser, block))

    suspend fun transactional(
        byUser: String? = null,
        transactions: Collection<Transaction>
    ): Result<List<Any>, Error> =
        Ok(crudRepository.transactional(byUser, transactions))

    suspend fun add(
        entities: Collection<T>,
        updateIfExists: Boolean = true,
        byUser: String? = null,
    ): Result<List<T>, Error> = Ok(crudRepository.save(entities, updateIfExists, byUser))

    suspend fun update(
        fieldValues: Map<String, @Contextual Any?>,
        predicate: BooleanVariable? = null,
        byUser: String? = null
    ): Result<List<Long>, Error> =
        Ok(crudRepository.update(fieldValues, predicate, byUser))

    suspend fun get(
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Result<Flow<T>, Error> = Ok(crudRepository.find(sort, predicate, limitOffset))

    suspend fun get(
        projections: Collection<Variable>,
        sort: Collection<Order>? = null,
        predicate: BooleanVariable? = null,
        limitOffset: LimitOffset? = null,
    ): Result<Flow<List<Any?>>, Error> = Ok(crudRepository.find(projections, sort, predicate, limitOffset))

    suspend fun remove(predicate: BooleanVariable? = null): Result<Long, Error> = Ok(crudRepository.delete(predicate))

    suspend fun aggregate(
        aggregate: core.expression.aggregate.AggregateExpression,
        predicate: BooleanVariable? = null,
    ): Result<Number, Error> = Ok(crudRepository.aggregate(aggregate, predicate))
}