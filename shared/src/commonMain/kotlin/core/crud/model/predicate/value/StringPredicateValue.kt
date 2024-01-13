package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
open class StringPredicateValue private constructor(override val value: String) : AbstractPredicateValue<String>() {
    companion object {
        fun string(value: String) = StringPredicateValue(value)
    }
}
