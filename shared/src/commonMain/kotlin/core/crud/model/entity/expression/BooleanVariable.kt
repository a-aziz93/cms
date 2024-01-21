package core.crud.model.entity.expression

import core.crud.model.entity.expression.logic.Logic

interface BooleanVariable : Variable {
    fun eq(value: BooleanVariable) =
        Logic.eq(this, value)

    fun neq(value: BooleanVariable) =
        Logic.neq(this, value)

    fun and(vararg values: BooleanVariable) =
        Logic.and(this, *values)

    fun or(vararg values: BooleanVariable) =
        Logic.or(this, *values)

    fun xor(vararg values: BooleanVariable) =
        Logic.xor(this, *values)

    fun negate() = Logic.negate(this)
}