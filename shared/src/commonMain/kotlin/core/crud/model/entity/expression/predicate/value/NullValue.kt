package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.predicate.Variable

class NullValue private constructor(override val value: Byte = 0) : Value<Byte> {
    companion object {
        fun nul() = NullValue()
    }
}