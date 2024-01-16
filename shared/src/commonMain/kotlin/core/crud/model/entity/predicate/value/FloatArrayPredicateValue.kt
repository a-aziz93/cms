package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class FloatArrayPredicateValue private constructor(override val value: FloatArray) : PredicateValue<FloatArray> {
    companion object {
        fun floatArray(value: FloatArray) = FloatArrayPredicateValue(value)
    }
}
