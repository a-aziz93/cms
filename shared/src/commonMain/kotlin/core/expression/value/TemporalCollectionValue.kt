package core.expression.value

import core.crud.repository.model.expression.variable.TemporalCollectionVariable
import kotlinx.serialization.Serializable
import java.time.temporal.Temporal

@Serializable
class TemporalCollectionValue<T : Temporal> private constructor(override val value: Collection<T>) :
    CollectionValue<T>, TemporalCollectionVariable {
    companion object {
        fun <T : Temporal> temporalCollection(value: Collection<T>) = TemporalCollectionValue(value)
    }
}