package core.expression.logic

import core.expression.variable.BooleanVariable
import core.expression.variable.Variable
import kotlinx.serialization.Serializable
import core.expression.logic.LogicExpressionType.*
import core.expression.variable.CollectionVariable
import core.expression.variable.StringVariable

@Serializable
class LogicExpression(
    val type: LogicExpressionType,
    vararg val values: Variable,
) : BooleanVariable {

    companion object {
        fun and(vararg values: BooleanVariable) =
            LogicExpression(AND, *values)

        fun or(vararg values: BooleanVariable) =
            LogicExpression(OR, *values)

        fun xor(vararg values: BooleanVariable) =
            LogicExpression(XOR, *values)

        fun negate(value: BooleanVariable) =
            LogicExpression(NOT, value)

        fun eq(leftValue: Variable, rightValue: Variable) =
            LogicExpression(EQUALS, leftValue, rightValue)

        fun neq(leftValue: Variable, rightValue: Variable) =
            LogicExpression(NOT_EQUALS, leftValue, rightValue)

        fun gt(leftValue: Variable, rightValue: Variable) =
            LogicExpression(GREATER_THAN, leftValue, rightValue)

        fun gte(leftValue: Variable, rightValue: Variable) =
            LogicExpression(GREATER_THAN_EQUAL, leftValue, rightValue)

        fun lt(leftValue: Variable, rightValue: Variable) =
            LogicExpression(LESS_THAN, leftValue, rightValue)

        fun lte(leftValue: Variable, rightValue: Variable) =
            LogicExpression(LESS_THAN_EQUAL, leftValue, rightValue)

        fun like(leftValue: StringVariable, rightValue: StringVariable) =
            LogicExpression(LIKE, leftValue, rightValue)

        fun between(value: Variable, leftValue: Variable, rightValue: Variable) =
            LogicExpression(BETWEEN, value, leftValue, rightValue)

        fun `in`(leftValue: Variable, rightValue: CollectionVariable) =
            LogicExpression(IN, leftValue, rightValue)

        fun nin(leftValue: Variable, rightValue: CollectionVariable) =
            LogicExpression(NIN, leftValue, rightValue)
    }
}