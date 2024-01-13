package core.crud.model.predicate.operation

import core.crud.model.predicate.PredicateValue
import core.crud.model.predicate.operation.CompareOperation.*

class ComparePredicate private constructor(
    @Suppress("UNUSED")
    operation: CompareOperation,
    vararg values: PredicateValue
) : Predicate(operation, *values) {
    companion object {
        fun eq(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(EQUALS, leftValue, rightValue)
        fun neq(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(NOT_EQUALS, leftValue, rightValue)
        fun gt(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(GREATER_THAN, leftValue, rightValue)
        fun gte(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(GREATER_THAN_EQUAL, leftValue, rightValue)
        fun lt(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(LESS_THAN, leftValue, rightValue)
        fun lte(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(LESS_THAN_EQUAL, leftValue, rightValue)
        fun btw(leftValue: PredicateValue, middleValue: PredicateValue, rightValue: PredicateValue) =
            ComparePredicate(BETWEEN, leftValue, middleValue, rightValue)

        fun like(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(LIKE, leftValue, rightValue)
        fun include(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate(IN, leftValue, rightValue)
    }
}