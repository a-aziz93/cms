package core.crud.model.entity.expression.arithmetic

import core.crud.model.entity.expression.NumberVariable
import core.crud.model.entity.expression.arithmetic.ArithmeticExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Arithmetic(
    val operation: ArithmeticExpression,
    vararg val values: NumberVariable,
) : NumberVariable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (Arithmetic) -> D): D {
        val arithmetics = mutableListOf<Triple<Arithmetic, Int, D?>>(Triple(this, 0, null))

        while (arithmetics.size > 0) {

        }

        return arithmetics.first().third!!
    }

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