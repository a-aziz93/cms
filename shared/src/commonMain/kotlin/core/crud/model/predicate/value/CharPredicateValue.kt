package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class CharPredicateValue private constructor(override val value: Char) : PredicateValue<Char> {
    companion object {
        fun char(value: Char) = CharPredicateValue(value)
    }
}
