package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class BooleanPredicateValue private constructor(override val value: Boolean) : PredicateValue<Boolean> {
    companion object {
        fun boolean(value: Boolean) = BooleanPredicateValue(value)
    }
}
