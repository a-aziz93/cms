package core.crud.model.entity.expression

import core.crud.model.entity.expression.logic.Logic

interface BooleanVariable : Variable {
    fun eq(value: BooleanVariable) =
        Logic.Companion.eq(this, value)

    fun neq(value: BooleanVariable) =
        Logic.Companion.neq(this, value)

    fun and(vararg values: BooleanVariable) =
        Logic.Companion.and(this, *values)

    fun or(vararg values: BooleanVariable) =
        Logic.Companion.or(this, *values)

    fun xor(vararg values: BooleanVariable) =
        Logic.Companion.xor(this, *values)

    fun no() = Logic.Companion.no(this)
}