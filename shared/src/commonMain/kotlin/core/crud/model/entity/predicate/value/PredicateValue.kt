package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.PredicateVariable
import core.crud.model.entity.predicate.operation.ComparePredicate

interface PredicateValue<T : Any> : PredicateVariable {
    val value: T

    fun eq(value: PredicateVariable) = ComparePredicate.eq(this, value)

    @Suppress("UNUSED")
    fun neq(value: PredicateVariable) = ComparePredicate.neq(this, value)

    @Suppress("UNUSED")
    fun gt(value: PredicateVariable) = ComparePredicate.gt(this, value)

    @Suppress("UNUSED")
    fun gte(value: PredicateVariable) = ComparePredicate.gte(this, value)

    @Suppress("UNUSED")
    fun lt(value: PredicateVariable) = ComparePredicate.lt(this, value)

    @Suppress("UNUSED")
    fun lte(value: PredicateVariable) = ComparePredicate.lte(this, value)

    @Suppress("UNUSED")
    fun btw(
        leftValue: PredicateVariable,
        rightValue: PredicateVariable
    ) =
        ComparePredicate.btw(leftValue, this, rightValue)

    @Suppress("UNUSED")
    fun like(value: PredicateVariable) = ComparePredicate.like(this, value)

    @Suppress("UNUSED")
    fun ln(value: PredicateVariable) = ComparePredicate.ln(this, value)

    @Suppress("UNUSED")
    fun nin(value: PredicateVariable) = ComparePredicate.nin(this, value)

}