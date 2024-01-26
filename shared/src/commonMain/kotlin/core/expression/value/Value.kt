package core.expression.value

import core.expression.variable.Variable

interface Value<T : Any> : Variable {
    val value: T
}