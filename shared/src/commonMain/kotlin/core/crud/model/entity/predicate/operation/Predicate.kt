package core.crud.model.entity.predicate.operation

import core.crud.model.entity.predicate.*
import core.crud.model.entity.predicate.operation.PredicateOperation.*
import core.crud.model.entity.predicate.value.CollectionValue
import kotlinx.serialization.Serializable

@Serializable
class Predicate(
    val operation: PredicateOperation,
    vararg val values: Variable,
) : BooleanVariable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (predicate: Predicate) -> D): D {
        val predicates = mutableListOf<Triple<Predicate, Int, D?>>(Triple(this, 0, null))

        while (predicates.size > 0) {

        }

        return predicates.first().third!!
    }

    companion object {
        fun and(vararg values: BooleanVariable) = Predicate(PredicateOperation.AND, *values)

        fun or(vararg values: BooleanVariable) = Predicate(PredicateOperation.OR, *values)

        fun xor(vararg values: BooleanVariable) = Predicate(PredicateOperation.XOR, *values)

        fun no(value: BooleanVariable) = Predicate(PredicateOperation.NOT, value)

        fun eq(leftValue: Variable, rightValue: Variable) =
            Predicate(EQUALS, leftValue, rightValue)

        fun neq(leftValue: Variable, rightValue: Variable) =
            Predicate(NOT_EQUALS, leftValue, rightValue)

        fun gt(leftValue: Variable, rightValue: Variable) =
            Predicate(GREATER_THAN, leftValue, rightValue)

        fun gte(leftValue: Variable, rightValue: Variable) =
            Predicate(GREATER_THAN_EQUAL, leftValue, rightValue)

        fun lt(leftValue: Variable, rightValue: Variable) =
            Predicate(LESS_THAN, leftValue, rightValue)

        fun lte(leftValue: Variable, rightValue: Variable) =
            Predicate(LESS_THAN_EQUAL, leftValue, rightValue)

        fun like(leftValue: StringVariable, rightValue: StringVariable) =
            Predicate(LIKE, leftValue, rightValue)

        fun between(value: Variable, leftValue: Variable, rightValue: Variable) =
            Predicate(BETWEEN, value, leftValue, rightValue)

        fun ln(leftValue: Variable, rightValue: CollectionVariable) =
            Predicate(IN, leftValue, rightValue)

        fun nin(leftValue: Variable, rightValue: CollectionVariable) =
            Predicate(NIN, leftValue, rightValue)
    }
}