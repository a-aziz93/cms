package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class ShortArrayPredicateValue private constructor(override val value: ShortArray) : PredicateValue<ShortArray> {
    companion object {
        fun shortArray(value: ShortArray) = ShortArrayPredicateValue(value)
    }
}
