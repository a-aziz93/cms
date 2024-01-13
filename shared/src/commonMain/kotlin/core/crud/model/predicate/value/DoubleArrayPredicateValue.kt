package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class DoubleArrayPredicateValue private constructor(override val value: DoubleArray) : PredicateValue<DoubleArray> {
    companion object {
        fun doubleArray(value: DoubleArray) = DoubleArrayPredicateValue(value)
    }
}
