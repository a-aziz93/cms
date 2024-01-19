package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.Variable

interface Value<T : Any> : Variable {
    val value: T
}