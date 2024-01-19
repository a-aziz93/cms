package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.BooleanVariable
import core.crud.model.entity.predicate.operation.Predicate
import kotlinx.serialization.Serializable

@Serializable
class BooleanValue private constructor(override val value: Boolean) : Value<Boolean>, BooleanVariable {
    companion object {
        fun boolean(value: Boolean) = BooleanValue(value)
    }
}
