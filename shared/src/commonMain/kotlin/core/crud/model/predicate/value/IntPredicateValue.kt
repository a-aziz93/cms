package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class IntPredicateValue private constructor(override val value: Int) : PredicateValue<Int> {
    companion object {
        fun int(value: Int) = IntPredicateValue(value)
    }
}
