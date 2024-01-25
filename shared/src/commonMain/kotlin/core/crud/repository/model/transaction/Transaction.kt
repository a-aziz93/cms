package core.crud.repository.model.transaction

import core.expression.variable.BooleanVariable
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
    data class DeleteTransaction(
        val predicate: BooleanVariable? = null,
    ) : Transaction()
}