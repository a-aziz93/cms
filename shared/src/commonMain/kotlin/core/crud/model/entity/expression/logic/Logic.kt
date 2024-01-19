package core.crud.model.entity.expression.logic

import core.crud.model.entity.expression.logic.LogicExpression.*
import core.crud.model.entity.expression.BooleanVariable
import core.crud.model.entity.expression.CollectionVariable
import core.crud.model.entity.expression.StringVariable
import core.crud.model.entity.expression.Variable
import kotlinx.serialization.Serializable

@Serializable
class Logic(
    val operation: LogicExpression,
    vararg val values: Variable,
) : BooleanVariable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (Logic) -> D): D {
        val predicates = mutableListOf<Triple<Logic, Int, D?>>(Triple(this, 0, null))

        while (predicates.size > 0) {

        }

        return predicates.first().third!!
    }

    companion object {
        fun and(vararg values: BooleanVariable) =
            Logic(AND, *values)

        fun or(vararg values: BooleanVariable) =
            Logic(OR, *values)

        fun xor(vararg values: BooleanVariable) =
            Logic(XOR, *values)

        fun no(value: BooleanVariable) =
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

        fun ln(leftValue: Variable, rightValue: CollectionVariable) =
            Logic(IN, leftValue, rightValue)

        fun nin(leftValue: Variable, rightValue: CollectionVariable) =
            Logic(NIN, leftValue, rightValue)
    }
}