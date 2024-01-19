package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.operation.PredicateOperation
import core.crud.model.entity.predicate.value.NumberCollectionValue

interface NumberVariable : Variable {
    fun eq(value: NumberVariable) =
        Predicate.eq(this, value)

    fun neq(value: NumberVariable) =
        Predicate.neq(this, value)

    fun gt(value: NumberVariable) =
        Predicate.gt(this, value)

    fun gte(value: NumberVariable) =
        Predicate.gte(this, value)

    fun lt(value: NumberVariable) =
        Predicate.lt(this, value)

    fun lte(value: NumberVariable) =
        Predicate.lte(this, value)

    fun between(leftValue: NumberVariable, rightValue: NumberVariable) =
        Predicate.between(this, leftValue, rightValue)

    fun ln(value: NumberCollectionValue<*>) =
        Predicate.ln(this, value)

    fun nin(value: NumberCollectionValue<*>) =
        Predicate.nin(this, value)
}