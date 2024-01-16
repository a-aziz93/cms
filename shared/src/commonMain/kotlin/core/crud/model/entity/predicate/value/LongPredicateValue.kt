package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class LongPredicateValue private constructor(override val value: Long) : PredicateValue<Long> {
    companion object {
        fun long(value: Long) = LongPredicateValue(value)
    }
}
