package core.crud.model.entity.expression.arithmetic

import core.crud.model.entity.expression.predicate.PredicateExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Arithmetic(
    val operation: ArithmeticExpression,
    vararg val values: core.crud.model.entity.expression.predicate.Variable,
) {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (predicate: core.crud.model.entity.expression.predicate.Predicate) -> D): D {
        val predicates = mutableListOf<Triple<core.crud.model.entity.expression.predicate.Predicate, Int, D?>>(Triple(this, 0, null))

        while (predicates.size > 0) {

        }

        return predicates.first().third!!
    }

    companion object {
        fun add(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(AND, *values)

        fun subtract(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(OR, *values)

        fun multiply(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(XOR, *values)

        fun divide(value: core.crud.model.entity.expression.predicate.BooleanVariable) =
            core.crud.model.entity.expression.predicate.Predicate(NOT, value)

        fun mod(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(EQUALS, leftValue, rightValue)

        fun power(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(NOT_EQUALS, leftValue, rightValue)

        fun square(leftValue: core.crud.model.entity.expression.predicate.Variable, rightValue: core.crud.model.entity.expression.predicate.Variable) =
            core.crud.model.entity.expression.predicate.Predicate(GREATER_THAN, leftValue, rightValue)
    }
}