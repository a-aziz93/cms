package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.TemporalCollectionVariable
import java.time.temporal.Temporal

class TemporalCollectionValue<T : Temporal> private constructor(override val value: Collection<T>) :
    CollectionValue<T>, TemporalCollectionVariable {
    companion object {
        fun <T : Temporal> temporalCollection(value: Collection<T>) = TemporalCollectionValue(value)
    }
}