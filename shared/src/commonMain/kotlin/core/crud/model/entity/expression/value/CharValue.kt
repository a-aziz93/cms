package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.CharVariable
import kotlinx.serialization.Serializable

@Serializable
class CharValue private constructor(override val value: Char) : Value<Char>,
    CharVariable {
    companion object {
        fun char(value: Char) = CharValue(value)
    }
}
