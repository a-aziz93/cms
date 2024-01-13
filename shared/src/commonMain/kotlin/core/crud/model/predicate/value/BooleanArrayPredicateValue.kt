package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class BooleanArrayPredicateValue private constructor(override val value: BooleanArray) : PredicateValue<BooleanArray> {
    companion object {
        fun booleanArray(value: BooleanArray) = BooleanArrayPredicateValue(value)
    }
}
