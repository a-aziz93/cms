package core.crud.model.entity.expression

import core.crud.model.entity.expression.logic.Logic
import core.crud.model.entity.expression.value.Value

interface Variable {
    fun eq(value: Value<*>) =
        Logic.eq(this, value)

    fun neq(value: Value<*>) =
        Logic.neq(this, value)
}