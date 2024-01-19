package core.crud.model.entity.predicate

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.value.CharCollectionValue
import core.crud.model.entity.predicate.value.StringCollectionValue

interface CharVariable : Variable {
    fun eq(value: CharVariable) =
        Predicate.eq(this, value)

    fun neq(value: CharVariable) =
        Predicate.neq(this, value)

    fun ln(value: CharCollectionVariable) =
        Predicate.ln(this, value)

    fun nin(value: CharCollectionVariable) =
        Predicate.nin(this, value)
}