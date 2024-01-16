package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class LongArrayPredicateValue private constructor(override val value: LongArray) : PredicateValue<LongArray> {
    companion object {
        fun longArray(value: LongArray) = LongArrayPredicateValue(value)
    }
}
