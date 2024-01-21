package core.crud.model.entity.expression.arithmetic

import core.crud.model.entity.expression.variable.NumberVariable
import core.crud.model.entity.expression.arithmetic.ArithmeticExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Arithmetic(
    val operation: ArithmeticExpression,
    vararg val values: NumberVariable,
) : NumberVariable {

    companion object {
        fun add(vararg values: NumberVariable) =
            Arithmetic(ADD, *values)

        fun subtract(vararg values: NumberVariable) =
            Arithmetic(SUBTRACT, *values)

        fun multiply(vararg values: NumberVariable) =
            Arithmetic(MULTIPLY, *values)

        fun divide(vararg values: NumberVariable) =
            Arithmetic(DIVIDE, *values)

        fun mod(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            Arithmetic(MOD, leftValue, rightValue)

        fun power(
            leftValue: NumberVariable,
            rightValue: NumberVariable,
        ) =
            Arithmetic(POWER, leftValue, rightValue)

        fun square(
            value: NumberVariable,
        ) =
            Arithmetic(SQUARE, value)
    }
}