package core.crud.model.entity.predicate.value

import java.time.temporal.Temporal

class TemporalCollectionValue<T : Temporal> private constructor(override val value: Collection<T>) :
    CollectionValue<T> {
    companion object {
        fun <T : Temporal> temporalCollection(value: Collection<T>) = TemporalCollectionValue(value)
    }
}