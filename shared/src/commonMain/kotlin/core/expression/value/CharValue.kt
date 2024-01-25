package core.expression.value

import core.crud.repository.model.expression.variable.CharVariable
import kotlinx.serialization.Serializable

@Serializable
class CharValue private constructor(override val value: Char) : Value<Char>,
    CharVariable {
    companion object {
        fun char(value: Char) = CharValue(value)
    }
}
