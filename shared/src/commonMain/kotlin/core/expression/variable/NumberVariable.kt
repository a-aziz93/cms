package core.expression.variable

import core.expression.arithmetic.Arithmetic
import core.crud.repository.model.expression.logic.Logic

interface NumberVariable : Variable {
    fun add(vararg values: NumberVariable) =
        core.expression.arithmetic.Arithmetic.add(this, *values)

    fun subtract(vararg values: NumberVariable) =
        core.expression.arithmetic.Arithmetic.subtract(this, *values)

    fun multiply(vararg values: NumberVariable) =
        core.expression.arithmetic.Arithmetic.multiply(this, *values)

    fun divide(vararg values: NumberVariable) =
        core.expression.arithmetic.Arithmetic.divide(this, *values)

    fun mod(
        value: NumberVariable,
    ) =
        core.expression.arithmetic.Arithmetic.mod(this, value)

    fun power(
        value: NumberVariable,
    ) =
        core.expression.arithmetic.Arithmetic.power(this, value)

    fun square() =
        core.expression.arithmetic.Arithmetic.square(this)

    fun eq(value: NumberVariable) =
        Logic.eq(this, value)

    fun neq(value: NumberVariable) =
        Logic.neq(this, value)

    fun gt(value: NumberVariable) =
        Logic.gt(this, value)

    fun gte(value: NumberVariable) =
        Logic.gte(this, value)

    fun lt(value: NumberVariable) =
        Logic.lt(this, value)

    fun lte(value: NumberVariable) =
        Logic.lte(this, value)

    fun between(leftValue: NumberVariable, rightValue: NumberVariable) =
        Logic.between(this, leftValue, rightValue)

    fun `in`(value: NumberCollectionVariable) =
        Logic.`in`(this, value)

    fun nin(value: NumberCollectionVariable) =
        Logic.nin(this, value)
}