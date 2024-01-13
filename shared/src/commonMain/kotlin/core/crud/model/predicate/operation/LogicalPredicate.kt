package core.crud.model.predicate.operation

import core.crud.model.predicate.PredicateVariable
import core.crud.model.predicate.operation.LogicalOperation.*

class LogicalPredicate private constructor(
    operation: LogicalOperation,
    vararg values: PredicateVariable
) : Predicate(operation, *values) {
    companion object {
        fun and(vararg values: PredicateVariable) = LogicalPredicate(AND, *values)
        fun or(vararg values: PredicateVariable) = LogicalPredicate(OR, *values)
        fun not(value: PredicateVariable): LogicalPredicate = LogicalPredicate(NOT, value)
    }
}