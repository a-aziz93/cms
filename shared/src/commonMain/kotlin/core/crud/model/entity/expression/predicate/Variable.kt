package core.crud.model.entity.expression.predicate

import core.crud.model.entity.predicate.value.Value

interface Variable {
    fun eq(value: Value<*>) =
        core.crud.model.entity.expression.predicate.Predicate.eq(this, value)

    fun neq(value: Value<*>) =
        core.crud.model.entity.expression.predicate.Predicate.neq(this, value)
}