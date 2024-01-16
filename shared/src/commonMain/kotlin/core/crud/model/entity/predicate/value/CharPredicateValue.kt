package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class CharPredicateValue private constructor(override val value: Char) : PredicateValue<Char> {
    companion object {
        fun char(value: Char) = CharPredicateValue(value)
    }
}
