package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.BooleanVariable
import kotlinx.serialization.Serializable

@Serializable
class BooleanValue private constructor(override val value: Boolean) : Value<Boolean>,
    BooleanVariable {
    companion object {
        fun boolean(value: Boolean) = BooleanValue(value)
    }
}
