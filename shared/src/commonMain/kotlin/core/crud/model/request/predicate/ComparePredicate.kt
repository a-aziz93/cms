package core.crud.model.request.predicate

import core.crud.model.request.predicate.CompareOperation.*

class ComparePredicate private constructor(
    @Suppress("UNUSED")
    operation: CompareOperation,
    vararg values: Any
) : Predicate<CompareOperation>(operation, *values) {
    companion object {
        fun eq(leftValue: Any, rightValue: Any) = ComparePredicate(EQUALS, leftValue, rightValue)
        fun neq(leftValue: Any, rightValue: Any) = ComparePredicate(NOT_EQUALS, leftValue, rightValue)
        fun gt(leftValue: Any, rightValue: Any) = ComparePredicate(GREATER_THAN, leftValue, rightValue)
        fun gte(leftValue: Any, rightValue: Any) = ComparePredicate(GREATER_THAN_EQUAL, leftValue, rightValue)
        fun lt(leftValue: Any, rightValue: Any) = ComparePredicate(LESS_THAN, leftValue, rightValue)
        fun lte(leftValue: Any, rightValue: Any) = ComparePredicate(LESS_THAN_EQUAL, leftValue, rightValue)
        fun btw(leftValue: Any, middleValue: Any, rightValue: Any) =
            ComparePredicate(BETWEEN, leftValue, middleValue, rightValue)

        fun like(leftValue: Any, rightValue: Any) = ComparePredicate(LIKE, leftValue, rightValue)
    }
}