package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class FloatPredicateValue private constructor(override val value: Float) : PredicateValue<Float> {
    companion object {
        fun float(value: Float) = FloatPredicateValue(value)
    }
}
