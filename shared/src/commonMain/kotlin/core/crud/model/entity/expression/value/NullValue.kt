package core.crud.model.entity.expression.value

import kotlinx.serialization.Serializable

@Serializable
class NullValue private constructor(override val value: Byte = 0) : Value<Byte> {
    companion object {
        fun nul() = NullValue()
    }
}