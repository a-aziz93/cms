package core.expression.logic

import core.crud.repository.model.expression.logic.LogicExpression.*
import core.crud.repository.model.expression.variable.BooleanVariable
import core.crud.repository.model.expression.variable.CollectionVariable
import core.crud.repository.model.expression.variable.StringVariable
import core.crud.repository.model.expression.variable.Variable
import kotlinx.serialization.Serializable

@Serializable
class Logic(
    val operation: LogicExpression,
    vararg val values: Variable,
) : BooleanVariable {

    companion object {
        fun and(vararg values: BooleanVariable) =
            Logic(AND, *values)

        fun or(vararg values: BooleanVariable) =
            Logic(OR, *values)

        fun xor(vararg values: BooleanVariable) =
            Logic(XOR, *values)

        fun negate(value: BooleanVariable) =
            Logic(NOT, value)

        fun eq(leftValue: Variable, rightValue: Variable) =
            Logic(EQUALS, leftValue, rightValue)

        fun neq(leftValue: Variable, rightValue: Variable) =
            Logic(NOT_EQUALS, leftValue, rightValue)

        fun gt(leftValue: Variable, rightValue: Variable) =
            Logic(GREATER_THAN, leftValue, rightValue)

        fun gte(leftValue: Variable, rightValue: Variable) =
            Logic(GREATER_THAN_EQUAL, leftValue, rightValue)

        fun lt(leftValue: Variable, rightValue: Variable) =
            Logic(LESS_THAN, leftValue, rightValue)

        fun lte(leftValue: Variable, rightValue: Variable) =
            Logic(LESS_THAN_EQUAL, leftValue, rightValue)

        fun like(leftValue: StringVariable, rightValue: StringVariable) =
            Logic(LIKE, leftValue, rightValue)

        fun between(value: Variable, leftValue: Variable, rightValue: Variable) =
            Logic(BETWEEN, value, leftValue, rightValue)

        fun `in`(leftValue: Variable, rightValue: CollectionVariable) =
            Logic(IN, leftValue, rightValue)

        fun nin(leftValue: Variable, rightValue: CollectionVariable) =
            Logic(NIN, leftValue, rightValue)
    }
}