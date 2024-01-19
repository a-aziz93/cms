package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.expression.predicate.BooleanVariable
import kotlinx.serialization.Serializable

@Serializable
class BooleanValue private constructor(override val value: Boolean) : Value<Boolean>,
    core.crud.model.entity.expression.predicate.BooleanVariable {
    companion object {
        fun boolean(value: Boolean) = BooleanValue(value)
    }
}
