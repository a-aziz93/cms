package core.expression.arithmetic

import core.expression.arithmetic.ArithmeticExpressionType.*
import core.expression.variable.NumberVariable
import kotlinx.serialization.Serializable

@Serializable
class ArithmeticExpression(
    val type: ArithmeticExpressionType,
    vararg val values: NumberVariable,
) : NumberVariable {

    companion object {
        fun add(vararg values: NumberVariable) =
            core.expression.arithmetic.ArithmeticExpression(ADD, *values)

        fun subtract(vararg values: NumberVariable) =
            core.expression.arithmetic.ArithmeticExpression(SUBTRACT, *values)

        fun multiply(vararg values: NumberVariable) =
            core.expression.arithmetic.ArithmeticExpression(MULTIPLY, *values)

        fun divide(vararg values: NumberVariable) =
            core.expression.arithmetic.ArithmeticExpression(DIVIDE, *values)

        fun mod(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            core.expression.arithmetic.ArithmeticExpression(MOD, leftValue, rightValue)

        fun power(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            core.expression.arithmetic.ArithmeticExpression(POWER, leftValue, rightValue)

        fun square(
            value: NumberVariable,
        ) =
            core.expression.arithmetic.ArithmeticExpression(SQUARE, value)
    }
}