package core.crud.model.entity.expression

import core.crud.model.entity.expression.logic.Logic

interface TemporalVariable : Variable {
    fun eq(value: TemporalVariable) =
        Logic.eq(this, value)

    fun neq(value: TemporalVariable) =
        Logic.neq(this, value)

    fun gt(value: TemporalVariable) =
        Logic.gt(this, value)

    fun gte(value: TemporalVariable) =
        Logic.gte(this, value)

    fun lt(value: TemporalVariable) =
        Logic.lt(this, value)

    fun lte(value: TemporalVariable) =
        Logic.lte(this, value)

    fun between(leftValue: TemporalVariable, rightValue: TemporalVariable) =
        Logic.between(this, leftValue, rightValue)

    fun ln(value: TemporalCollectionVariable) =
        Logic.ln(this, value)

    fun nin(value: TemporalCollectionVariable) =
        Logic.nin(this, value)
}