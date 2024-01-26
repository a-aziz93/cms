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
            ArithmeticExpression(ADD, *values)

        fun subtract(vararg values: NumberVariable) =
            ArithmeticExpression(SUBTRACT, *values)

        fun multiply(vararg values: NumberVariable) =
            ArithmeticExpression(MULTIPLY, *values)

        fun divide(vararg values: NumberVariable) =
            ArithmeticExpression(DIVIDE, *values)

        fun mod(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            ArithmeticExpression(MOD, leftValue, rightValue)

        fun power(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            ArithmeticExpression(POWER, leftValue, rightValue)

        fun square(
            value: NumberVariable,
        ) =
            ArithmeticExpression(SQUARE, value)
    }
}