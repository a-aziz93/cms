package core.crud.model.predicate.operation

import core.crud.model.predicate.PredicateValue
import core.crud.model.predicate.operation.LogicalOperation.*

class LogicalPredicate private constructor(
    operation: LogicalOperation,
    vararg values: PredicateValue
) : Predicate(operation, *values) {
    companion object {
        fun and(vararg values: PredicateValue) = LogicalPredicate(AND, *values)
        fun or(vararg values: PredicateValue) = LogicalPredicate(OR, *values)
        fun not(value: PredicateValue): LogicalPredicate = LogicalPredicate(NOT, value)
    }
}