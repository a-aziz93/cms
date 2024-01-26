package core.expression.variable

import core.expression.logic.LogicExpression

interface BooleanVariable : Variable {
    fun and(vararg values: BooleanVariable) =
        LogicExpression.and(this, *values)

    fun or(vararg values: BooleanVariable) =
        LogicExpression.or(this, *values)

    fun xor(vararg values: BooleanVariable) =
        LogicExpression.xor(this, *values)

    fun negate() = LogicExpression.negate(this)
}