package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.expression.predicate.CharVariable
import kotlinx.serialization.Serializable

@Serializable
class CharValue private constructor(override val value: Char) : Value<Char>,
    core.crud.model.entity.expression.predicate.CharVariable {
    companion object {
        fun char(value: Char) = CharValue(value)
    }
}
