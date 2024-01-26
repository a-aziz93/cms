package core.expression.variable

import core.expression.logic.LogicExpression

interface ComparableVariable : Variable {
    fun gt(value: ComparableVariable) =
        LogicExpression.gt(this, value)

    fun gte(value: ComparableVariable) =
        LogicExpression.gte(this as ComparableVariable, value)

    fun lt(value: ComparableVariable) =
        LogicExpression.lt(this as ComparableVariable, value)

    fun lte(value: ComparableVariable) =
        LogicExpression.lte(this as ComparableVariable, value)

    fun between(leftValue: ComparableVariable, rightValue: ComparableVariable) =
        LogicExpression.between(this, leftValue, rightValue)
}