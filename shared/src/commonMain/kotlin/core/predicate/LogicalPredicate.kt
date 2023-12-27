package graph.predicate

import graph.predicate.LogicalOperation.*

class LogicalPredicate private constructor(
    operation: LogicalOperation,
    vararg values: Any
) : Predicate<LogicalOperation>(operation, *values) {
    companion object {
        fun and(vararg values: Any) = LogicalPredicate(AND, *values)
        fun or(vararg values: Any) = LogicalPredicate(OR, *values)
        fun not(value: Any): LogicalPredicate = LogicalPredicate(NOT, value)
    }
}