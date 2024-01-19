package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.value.Value

interface Variable {
    fun eq(value: Value<*>) =
        Predicate.eq(this, value)

    fun neq(value: Value<*>) =
        Predicate.neq(this, value)
}