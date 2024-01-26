package core.crud.repository.model.transaction

import core.crud.repository.model.io.LimitOffset
import core.crud.repository.model.io.Order
import core.expression.aggregate.AggregateExpression
import core.expression.variable.BooleanVariable
import core.expression.variable.Variable
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Transaction {
    @Serializable
    data class SaveTransaction<T>(
        val entities: Collection<T>,
        val updateIfExists: Boolean = true,
    ) : Transaction()

    @Serializable
    data class UpdateTransaction(
        val fieldValues: Map<String, @Contextual Any?>,
        val predicate: BooleanVariable? = null,
    ) : Transaction()

    @Serializable
    data class FindTransaction(
        val projections: Collection<Variable>? = null,
        val sort: Collection<Order>? = null,
        val predicate: BooleanVariable? = null,
        val limitOffset: LimitOffset? = null,
    ) : Transaction()

    @Serializable
    data class DeleteTransaction(
        val predicate: BooleanVariable? = null,
    ) : Transaction()

    @Serializable
    data class AggregateTransaction(
        val aggregate: AggregateExpression,
        val predicate: BooleanVariable? = null,
    ) : Transaction()
}