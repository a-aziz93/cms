package core.expression.value

import core.crud.repository.model.expression.variable.Variable
import kotlinx.serialization.Serializable

@Serializable
class NullValue private constructor(override val value: Byte = 0) : Value<Byte>, Variable {
    companion object {
        fun nul() = NullValue()
    }
}