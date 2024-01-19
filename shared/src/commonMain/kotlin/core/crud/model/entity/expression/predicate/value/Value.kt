package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.predicate.Variable

interface Value<T : Any> : Variable {
    val value: T
}