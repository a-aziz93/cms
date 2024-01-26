package core.expression.logic

import kotlinx.serialization.Serializable
import core.expression.logic.LogicExpressionType.*
import core.expression.variable.*

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

        fun <T : Variable> eq(leftValue: T, rightValue: T) =
            LogicExpression(EQUALS, leftValue, rightValue)

        fun <T : Variable> neq(leftValue: T, rightValue: T) =
            LogicExpression(NOT_EQUALS, leftValue, rightValue)

        fun <T : ComparableVariable> gt(leftValue: T, rightValue: T) =
            LogicExpression(GREATER_THAN, leftValue, rightValue)

        fun <T : ComparableVariable> gte(leftValue: T, rightValue: T) =
            LogicExpression(GREATER_THAN_EQUAL, leftValue, rightValue)

        fun <T : ComparableVariable> lt(leftValue: T, rightValue: T) =
            LogicExpression(LESS_THAN, leftValue, rightValue)

        fun <T : ComparableVariable> lte(leftValue: T, rightValue: T) =
            LogicExpression(LESS_THAN_EQUAL, leftValue, rightValue)

        fun <T : ComparableVariable> between(
            value: T,
            leftValue: T,
            rightValue: T,
        ) =
            LogicExpression(BETWEEN, value, leftValue, rightValue)

        fun like(
            leftValue: StringVariable,
            rightValue: StringVariable
        ) =
            LogicExpression(LIKE, leftValue, rightValue)


        fun `in`(leftValue: Variable, rightValue: CollectionVariable) =
            LogicExpression(IN, leftValue, rightValue)

        fun nin(leftValue: Variable, rightValue: CollectionVariable) =
            LogicExpression(NIN, leftValue, rightValue)
    }
}