package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class CharArrayPredicateValue private constructor(override val value: CharArray) : PredicateValue<CharArray> {
    companion object {
        fun charArray(value: CharArray) = CharArrayPredicateValue(value)
    }
}
