package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate

interface BooleanVariable : Variable {
    fun eq(value: BooleanVariable) =
        Predicate.eq(this, value)

    fun neq(value: BooleanVariable) =
        Predicate.neq(this, value)

    fun and(vararg values: BooleanVariable) = Predicate.and(this, *values)

    fun or(vararg values: BooleanVariable) = Predicate.or(this, *values)

    fun xor(vararg values: BooleanVariable) = Predicate.xor(this, *values)

    fun no() = Predicate.no(this)
}