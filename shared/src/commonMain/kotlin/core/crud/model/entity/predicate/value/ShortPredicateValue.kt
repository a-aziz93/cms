package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class ShortPredicateValue private constructor(override val value: Short) : PredicateValue<Short> {
    companion object {
        fun short(value: Short) = ShortPredicateValue(value)
    }
}
