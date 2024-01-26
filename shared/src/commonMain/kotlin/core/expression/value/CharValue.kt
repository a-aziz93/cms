package core.expression.value

import core.expression.variable.CharVariable
import kotlinx.serialization.Serializable

@Serializable
class CharValue private constructor(override val value: Char) : Value<Char>,
    CharVariable {
    companion object {
        fun char(value: Char) = CharValue(value)
    }
}
