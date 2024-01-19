package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.operation.PredicateOperation
import core.crud.model.entity.predicate.value.StringCollectionValue

interface StringVariable : Variable {
    fun eq(value: StringVariable) =
        Predicate.eq(this, value)

    fun neq(value: StringVariable) =
        Predicate.neq(this, value)

    fun like(value: StringVariable) =
        Predicate.like(this, value)

    fun ln(value: StringCollectionValue) =
        Predicate.ln(this, value)

    fun nin(value: StringCollectionValue) =
        Predicate.nin(this, value)
}