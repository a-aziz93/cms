package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class IntArrayPredicateValue private constructor(override val value: IntArray) : PredicateValue<IntArray> {
    companion object {
        fun intArray(value: IntArray) = IntArrayPredicateValue(value)
    }
}
