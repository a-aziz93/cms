package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.Variable

interface Value<T : Any> : Variable {
    val value: T
}