package core.expression.value

import core.expression.variable.BooleanVariable
import kotlinx.serialization.Serializable

@Serializable
class BooleanValue private constructor(override val value: Boolean) : Value<Boolean>,
    BooleanVariable {
    companion object {
        fun boolean(value: Boolean) = BooleanValue(value)
    }
}
