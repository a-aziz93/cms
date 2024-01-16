package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class IntPredicateValue private constructor(override val value: Int) : PredicateValue<Int> {
    companion object {
        fun int(value: Int) = IntPredicateValue(value)
    }
}
