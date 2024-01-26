package core.expression.value

import core.expression.variable.Variable
import kotlinx.serialization.Serializable

@Serializable
class NullValue private constructor(override val value: Byte = 0) : Value<Byte>, Variable {
    companion object {
        fun nul() = NullValue()
    }
}