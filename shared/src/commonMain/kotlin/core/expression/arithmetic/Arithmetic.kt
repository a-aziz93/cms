package core.expression.arithmetic

import core.crud.repository.model.expression.variable.NumberVariable
import core.expression.arithmetic.ArithmeticExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Arithmetic(
    val operation: core.expression.arithmetic.ArithmeticExpression,
    vararg val values: NumberVariable,
) : NumberVariable {

    companion object {
        fun add(vararg values: NumberVariable) =
            core.expression.arithmetic.Arithmetic(ADD, *values)

        fun subtract(vararg values: NumberVariable) =
            core.expression.arithmetic.Arithmetic(SUBTRACT, *values)

        fun multiply(vararg values: NumberVariable) =
            core.expression.arithmetic.Arithmetic(MULTIPLY, *values)

        fun divide(vararg values: NumberVariable) =
            core.expression.arithmetic.Arithmetic(DIVIDE, *values)

        fun mod(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            core.expression.arithmetic.Arithmetic(MOD, leftValue, rightValue)

        fun power(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            core.expression.arithmetic.Arithmetic(POWER, leftValue, rightValue)

        fun square(
            value: NumberVariable,
        ) =
            core.expression.arithmetic.Arithmetic(SQUARE, value)
    }
}