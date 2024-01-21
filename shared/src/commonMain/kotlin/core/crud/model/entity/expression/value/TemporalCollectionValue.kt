package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.variable.TemporalCollectionVariable
import kotlinx.serialization.Serializable
import java.time.temporal.Temporal

@Serializable
class TemporalCollectionValue<T : Temporal> private constructor(override val value: Collection<T>) :
    CollectionValue<T>, TemporalCollectionVariable {
    companion object {
        fun <T : Temporal> temporalCollection(value: Collection<T>) = TemporalCollectionValue(value)
    }
}