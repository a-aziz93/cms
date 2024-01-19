package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.CharVariable
import kotlinx.serialization.Serializable

@Serializable
class CharValue private constructor(override val value: Char) : Value<Char>, CharVariable {
    companion object {
        fun char(value: Char) = CharValue(value)
    }
}
