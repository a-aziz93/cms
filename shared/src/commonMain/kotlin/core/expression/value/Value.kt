package core.expression.value

import core.crud.repository.model.expression.variable.Variable

interface Value<T : Any> : Variable {
    val value: T
}