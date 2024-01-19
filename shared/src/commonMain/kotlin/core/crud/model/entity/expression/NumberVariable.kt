package core.crud.model.entity.expression

import core.crud.model.entity.expression.arithmetic.Arithmetic
import core.crud.model.entity.expression.logic.Logic

interface NumberVariable : Variable {
    fun add(vararg values: NumberVariable) =
        Arithmetic.add(this, *values)

    fun subtract(vararg values: NumberVariable) =
        Arithmetic.subtract(this, *values)

    fun multiply(vararg values: NumberVariable) =
        Arithmetic.multiply(this, *values)

    fun divide(vararg values: NumberVariable) =
        Arithmetic.divide(this, *values)

    fun mod(
        value: NumberVariable,
    ) =
        Arithmetic.mod(this, value)

    fun power(
        value: NumberVariable,
    ) =
        Arithmetic.power(this, value)

    fun square() =
        Arithmetic.square(this)

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

    fun ln(value: NumberCollectionVariable) =
        Logic.ln(this, value)

    fun nin(value: NumberCollectionVariable) =
        Logic.nin(this, value)

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (NumberVariable) -> D): D {
        val numberVariables = mutableListOf<Triple<NumberVariable, Int, D?>>(Triple(this, 0, null))

        while (numberVariables.size > 0) {

        }

        return numberVariables.first().third!!
    }
}