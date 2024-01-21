package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.variable.Variable

interface Value<T : Any> : Variable {
    val value: T
}