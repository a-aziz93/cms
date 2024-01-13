package core.crud.model.predicate.operation

import core.crud.model.predicate.PredicateVariable
import core.crud.model.predicate.operation.CompareOperation.*
import kotlinx.serialization.Serializable

class ComparePredicate private constructor(
    @Suppress("UNUSED")
    operation: CompareOperation,
    vararg values: PredicateVariable
) : Predicate(operation, *values) {
    companion object {
        fun eq(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(EQUALS, leftValue, rightValue)
        fun neq(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(NOT_EQUALS, leftValue, rightValue)
        fun gt(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(GREATER_THAN, leftValue, rightValue)
        fun gte(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(GREATER_THAN_EQUAL, leftValue, rightValue)
        fun lt(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(LESS_THAN, leftValue, rightValue)
        fun lte(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(LESS_THAN_EQUAL, leftValue, rightValue)
        fun btw(leftValue: PredicateVariable, middleValue: PredicateVariable, rightValue: PredicateVariable) =
            ComparePredicate(BETWEEN, leftValue, middleValue, rightValue)

        fun like(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(LIKE, leftValue, rightValue)
        fun include(leftValue: PredicateVariable, rightValue: PredicateVariable) = ComparePredicate(IN, leftValue, rightValue)
    }
}