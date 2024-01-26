package core.expression.variable

import core.expression.arithmetic.ArithmeticExpression
import core.expression.logic.LogicExpression
import core.expression.arithmetic.ArithmeticExpression.Companion.mod
import core.expression.arithmetic.ArithmeticExpression.Companion.power
import core.expression.arithmetic.ArithmeticExpression.Companion.square
import core.expression.logic.LogicExpression.Companion.eq
import core.expression.logic.LogicExpression.Companion.neq
import core.expression.logic.LogicExpression.Companion.gt
import core.expression.logic.LogicExpression.Companion.gte
import core.expression.logic.LogicExpression.Companion.lt
import core.expression.logic.LogicExpression.Companion.lte
import core.expression.logic.LogicExpression.Companion.between
import core.expression.logic.LogicExpression.Companion.`in`
import core.expression.logic.LogicExpression.Companion.nin

interface NumberVariable : ComparableVariable {
    fun add(vararg values: NumberVariable) =
        ArithmeticExpression.add(this, *values)

    fun subtract(vararg values: NumberVariable) =
        ArithmeticExpression.subtract(this, *values)

    fun multiply(vararg values: NumberVariable) =
        ArithmeticExpression.multiply(this, *values)

    fun divide(vararg values: NumberVariable) =
        ArithmeticExpression.divide(this, *values)

    fun mod(
        value: NumberVariable,
    ) =
        mod(this, value)

    fun power(
        value: NumberVariable,
    ) =
        power(this, value)

    fun square() =
        square(this)

    fun `in`(value: NumberCollectionVariable) =
        `in`(this, value)

    fun nin(value: NumberCollectionVariable) =
        nin(this, value)
}