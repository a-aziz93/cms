package core.crud.model.entity.predicate.operation

import core.crud.model.entity.predicate.PredicateVariable
import core.crud.model.entity.predicate.operation.LogicalOperation.*

class LogicalPredicate private constructor(
    operation: LogicalOperation,
    vararg values: core.crud.model.entity.predicate.PredicateVariable
) : Predicate(operation, *values) {
    companion object {
        fun and(vararg values: core.crud.model.entity.predicate.PredicateVariable) = LogicalPredicate(AND, *values)
        fun or(vararg values: core.crud.model.entity.predicate.PredicateVariable) = LogicalPredicate(OR, *values)
        fun not(value: core.crud.model.entity.predicate.PredicateVariable): LogicalPredicate = LogicalPredicate(NOT, value)
    }
}