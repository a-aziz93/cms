package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.value.TemporalCollectionValue

interface TemporalVariable : Variable {
    fun eq(value: TemporalVariable) =
        Predicate.eq(this, value)

    fun neq(value: TemporalVariable) =
        Predicate.neq(this, value)

    fun gt(value: TemporalVariable) =
        Predicate.gt(this, value)

    fun gte(value: TemporalVariable) =
        Predicate.gte(this, value)

    fun lt(value: TemporalVariable) =
        Predicate.lt(this, value)

    fun lte(value: TemporalVariable) =
        Predicate.lte(this, value)

    fun between(leftValue: TemporalVariable, rightValue: TemporalVariable) =
        Predicate.between(this, leftValue, rightValue)

    fun ln(value: TemporalCollectionVariable) =
        Predicate.ln(this, value)

    fun nin(value: TemporalCollectionVariable) =
        Predicate.nin(this, value)
}