package core.expression.value

import core.expression.variable.NumberVariable
import kotlinx.serialization.Serializable

@Serializable
class NumberValue<T : Number>(override val value: T) : Value<T>,
    NumberVariable {
    companion object {
        fun <T : Number> number(value: T) = NumberValue(value)
    }
}
