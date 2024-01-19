package core.crud.model.entity.expression.predicate

import core.crud.model.entity.expression.predicate.PredicateExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Predicate(
    val operation: core.crud.model.entity.expression.predicate.PredicateExpression,
    vararg val values: core.crud.model.entity.expression.predicate.Variable,
) : core.crud.model.entity.expression.predicate.BooleanVariable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (predicate: core.crud.model.entity.expression.predicate.Predicate) -> D): D {
        val predicates = mutableListOf<Triple<core.crud.model.entity.expression.predicate.Predicate, Int, D?>>(Triple(this, 0, null))

        while (predicates.size > 0) {

        }

        return predicates.first().third!!
    }

    companion object {
        fun and(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(AND, *values)

        fun or(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(OR, *values)

        fun xor(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(XOR, *values)

        fun no(value: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(NOT, value)

        fun eq(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(EQUALS, leftValue, rightValue)

        fun neq(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(NOT_EQUALS, leftValue, rightValue)

        fun gt(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(GREATER_THAN, leftValue, rightValue)

        fun gte(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(GREATER_THAN_EQUAL, leftValue, rightValue)

        fun lt(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(LESS_THAN, leftValue, rightValue)

        fun lte(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(LESS_THAN_EQUAL, leftValue, rightValue)

        fun like(leftValue: core.crud.model.entity.expression.predicate.StringVariable, rightValue: core.crud.model.entity.expression.predicate.StringVariable) =
            core.crud.model.entity.expression.predicate.Predicate(LIKE, leftValue, rightValue)

        fun between(value: core.crud.model.entity.expression.predicate.Variable, leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(BETWEEN, value, leftValue, rightValue)

        fun ln(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.CollectionVariable) =
            core.crud.model.entity.expression.predicate.Predicate(IN, leftValue, rightValue)

        fun nin(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.CollectionVariable) =
            core.crud.model.entity.expression.predicate.Predicate(NIN, leftValue, rightValue)
    }
}