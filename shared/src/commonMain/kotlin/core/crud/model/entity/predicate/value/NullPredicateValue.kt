package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class NullPredicateValue private constructor(override val value: Boolean) : PredicateValue<Boolean> {
    companion object {
        fun nul() = NullPredicateValue(false)
    }
}
