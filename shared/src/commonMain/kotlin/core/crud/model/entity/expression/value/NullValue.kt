package core.crud.model.entity.expression.value

class NullValue private constructor(override val value: Byte = 0) : Value<Byte> {
    companion object {
        fun nul() = NullValue()
    }
}