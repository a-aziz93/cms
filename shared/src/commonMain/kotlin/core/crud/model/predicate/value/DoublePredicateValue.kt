package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class DoublePredicateValue private constructor(override val value: Double) : PredicateValue<Double> {
    companion object {
        fun double(value: Double) = DoublePredicateValue(value)
    }
}
